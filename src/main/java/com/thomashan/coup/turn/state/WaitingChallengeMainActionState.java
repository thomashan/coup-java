package com.thomashan.coup.turn.state;

import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.coup.Deck;
import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionDetector;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.ChallengeActionType;
import com.thomashan.coup.action.MainAction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.thomashan.coup.action.ChallengeActionType.CHALLENGE;
import static java.util.Optional.empty;

public final class WaitingChallengeMainActionState implements TurnState<ChallengeAction> {
    private final Players players;
    private final Player player;
    private final MainAction mainAction;
    private final ImmutableList<Action> actionHistory;
    private final Optional<Player> target;

    private WaitingChallengeMainActionState(Players players, Player player, MainAction mainAction, List<Action> actionHistory, Player target) {
        this.players = players;
        this.player = player;
        this.mainAction = mainAction;
        this.actionHistory = ImmutableList.of(actionHistory);

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    public static WaitingChallengeMainActionState of(Players players, Player player, MainAction mainAction, List<Action> actionHistory) {
        return new WaitingChallengeMainActionState(players, player, mainAction, actionHistory, null);
    }

    public static WaitingChallengeMainActionState of(Players players, Player player, MainAction mainAction, List<Action> actionHistory, Player target) {
        return new WaitingChallengeMainActionState(players, player, mainAction, actionHistory, target);
    }

    @Override
    public MainAction getMainAction() {
        return mainAction;
    }

    @Override
    public List<Action> getActionHistory() {
        return actionHistory;
    }

    @Override
    public Deck getDeck() {
        return null;
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
    public List<Player> getActionablePlayers() {
        // FIXME: return all players apart from the player who performed the main action
        return null;
    }

    @Override
    public Optional<Player> getTarget() {
        return target;
    }

    @Override
    public Optional<Player> getMainActionChallengedBy() {
        return empty();
    }

    @Override
    public Optional<Player> getBlockActionChallengedBy() {
        return empty();
    }

    @Override
    public Optional<ChallengeActionType> getChallengeActionType() {
        return empty();
    }

    @Override
    public Optional<BlockActionType> getBlockAction() {
        return empty();
    }

    @Override
    public Optional<ChallengeActionType> getBlockChallengeActionType() {
        return empty();
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public TurnState performAction(ChallengeAction action) {
        checkMainActionIsChallengeable();
        checkActionPlayerIsDifferentToMainActionPlayer(action);

        ImmutableList<Action> newActionHistory = actionHistory.plus(action);

        if (action.getChallengeActionType() == CHALLENGE) {
            if (ActionDetector.isBluff(mainAction.getActionType(), player.getActivePlayerCards().stream().map(c -> c.getCard()).collect(Collectors.toSet()))) {
                return WaitingRevealCardState.of(players, player, mainAction, newActionHistory, player);
            } else {
                return WaitingRevealCardState.of(players, player, mainAction, newActionHistory, action.getPlayer());
            }
        }

        if (mainAction.getActionType().isBlockable()) {
            return WaitingBlockActionState.of(players, player, mainAction, newActionHistory);
        }

        return CompletedState.of(players, player, mainAction, newActionHistory);
    }

    private void checkActionPlayerIsDifferentToMainActionPlayer(ChallengeAction action) {
        if (mainAction.getPlayer().equals(action.getPlayer())) {
            throw new IllegalArgumentException("You cannot challenge your own action");
        }
    }

    private void checkMainActionIsChallengeable() {
        if (!mainAction.getActionType().isChallengeable()) {
            throw new IllegalArgumentException("Challenging a non-challengeable action");
        }
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return Arrays.asList(ChallengeActionType.values());
    }
}
