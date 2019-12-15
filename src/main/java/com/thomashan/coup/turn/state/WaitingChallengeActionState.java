package com.thomashan.coup.turn.state;

import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.PerformAction;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.thomashan.coup.action.ChallengeActionType.ALLOW;
import static com.thomashan.coup.action.ChallengeActionType.CHALLENGE;
import static java.util.Optional.empty;

abstract class WaitingChallengeActionState implements TurnState<ChallengeAction> {
    private final Players players;
    private final Player player;
    private final Deck deck;
    private final MainAction mainAction;
    private final ImmutableList<Action> actionHistory;
    private final Optional<Player> target;
    private final ImmutableList<Player> challengeablePlayers;
    private final FromState fromState;

    protected WaitingChallengeActionState(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Player target, List<Player> challengeablePlayers, FromState fromState) {
        this.players = players;
        this.player = player;
        this.deck = deck;
        this.challengeablePlayers = ImmutableList.of(challengeablePlayers);
        this.mainAction = mainAction;
        this.actionHistory = ImmutableList.of(actionHistory);
        this.fromState = fromState;

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    @Override
    public List<Action> getActionHistory() {
        return actionHistory;
    }

    @Override
    public Deck getDeck() {
        return deck;
    }

    @Override
    public Players getPlayers() {
        return players;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Optional<Player> getTarget() {
        return target;
    }

    @Override
    public MainAction getMainAction() {
        return mainAction;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public boolean isCheckAllowableActions() {
        return true;
    }

    @Override
    public List<ChallengeAction> getAllowableActions() {
        return getChallengeablePlayers().stream()
                .flatMap(p -> Stream.of(ChallengeAction.of(p, ALLOW), ChallengeAction.of(p, CHALLENGE)))
                .collect(Collectors.toList());
    }

    protected abstract TurnState toWaitingChallengeActionState(ChallengeAction action, List<Action> actionHistory);

    @Override
    public TurnState performAction(ChallengeAction action) {
        checkAction(action);

        List<Action> newActionHistory = actionHistory.plus(action);

        if (action.getChallengeActionType() == CHALLENGE) {
            return toRevealCardState(action, newActionHistory);
        }

        if (getChallengeablePlayers().size() > 1) {
            checkPlayerCanChallengeOrAllow(action);
            return toWaitingChallengeActionState(action, newActionHistory);
        }

        switch (fromState) {
            case MAIN: {
                if (getMainAction().getActionType().isBlockable()) {
                    return WaitingBlockActionState.of(getPlayers(), getPlayer(), getDeck(), getMainAction(), newActionHistory, getTarget().orElse(null));
                }

                switch (getMainAction().getActionType()) {
                    case TAX:
                        return WaitingToPerformActionsState.of(getPlayers(), getPlayer(), getDeck(), getMainAction(), newActionHistory, getTarget().orElse(null))
                                .performAction(PerformAction.mainAction(getMainAction()));
                    default:
                        return CompletedState.of(getPlayers(), getPlayer(), getDeck(), getMainAction(), newActionHistory, getTarget().orElse(null));
                }
            }
            default:
                switch (getMainAction().getActionType()) {
                    case ASSASSINATE:
                        return WaitingToPerformActionsState.of(getPlayers(), getPlayer(), getDeck(), getMainAction(), newActionHistory, getTarget().orElse(null))
                                .performAction(PerformAction.takeCoinsForAssassination(getPlayer()));
                    default:
                        return CompletedState.of(getPlayers(), getPlayer(), getDeck(), getMainAction(), newActionHistory, getTarget().orElse(null));
                }
        }
    }

    private void checkPlayerCanChallengeOrAllow(ChallengeAction action) {
        if (!getChallengeablePlayers().contains(action.getPlayer())) {
            throw new IllegalArgumentException("This player can't make a challenge or allow the move");
        }
    }

    protected List<Player> getChallengeablePlayers() {
        return challengeablePlayers;
    }

    protected abstract TurnState toRevealCardState(ChallengeAction action, List<Action> actionHistory);

    protected abstract void checkAction(ChallengeAction action);

    public enum FromState {
        MAIN,
        BLOCK
    }
}
