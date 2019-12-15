package com.thomashan.coup.turn.state;

import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.BlockAction;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.PerformAction;
import com.thomashan.coup.action.RevealCardAction;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.thomashan.coup.action.ActionDetector.isBlockAction;
import static com.thomashan.coup.action.ActionDetector.isMainAction;
import static com.thomashan.coup.action.ActionDetector.isNoAction;
import static com.thomashan.coup.action.MainActionType.ASSASSINATE;
import static java.util.Optional.empty;

public final class WaitingRevealCardState implements TurnState<RevealCardAction> {
    private final Players players;
    private final Player player;
    private final Deck deck;
    private final MainAction mainAction;
    private final ImmutableList<Action> actionHistory;
    private final Action actionToPerform;
    private final Optional<Player> target;
    private final Player revealer;

    private WaitingRevealCardState(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Action actionToPerform, Player target, Player revealer) {
        this.players = players;
        this.player = player;
        this.deck = deck;
        this.mainAction = mainAction;
        this.actionHistory = ImmutableList.of(actionHistory);
        this.actionToPerform = actionToPerform;
        this.revealer = revealer;

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    static WaitingRevealCardState of(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Action actionToPerform, Player target, Player revealer) {
        return new WaitingRevealCardState(players, player, deck, mainAction, actionHistory, actionToPerform, target, revealer);
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
    public boolean isComplete() {
        return false;
    }

    @Override
    public boolean isCheckAllowableActions() {
        return true;
    }

    @Override
    public TurnState performAction(RevealCardAction action) {
        List<Action> newActionHistory = actionHistory.plus(action);
        Player newRevealer = getRevealer().revealCard(action.getCard());
        Players newPlayers = players.updatePlayer(getRevealer(), newRevealer);

        Optional<Player> newTarget = newTarget(newRevealer);
        Player newPlayer = newPlayer(newRevealer);

        if (isNoAction(actionToPerform)) {
            return CompletedState.of(newPlayers, newPlayer, getDeck(), getMainAction(), newActionHistory, getTarget().orElse(null));
        } else if (isMainAction(actionToPerform)) {
            if (newTarget.isPresent() && !newTarget.get().isActive() && actionToPerform.getActionType() == ASSASSINATE) {
                return WaitingToPerformActionsState.of(newPlayers, newPlayer, getDeck(), getMainAction(), newActionHistory, newTarget.orElse(null))
                        .performAction(PerformAction.takeCoinsForAssassination(newPlayer));
            }

            MainAction newMainAction = newMainAction(newPlayer, newTarget);
            if (newMainAction.getActionType().isBlockable()) {
                return WaitingBlockActionState.of(newPlayers, newPlayer, getDeck(), newMainAction, newActionHistory, newTarget.orElse(null));
            }

            return WaitingToPerformActionsState.of(newPlayers, newPlayer, getDeck(), newMainAction, newActionHistory, newTarget.orElse(null))
                    .performAction(PerformAction.mainAction(newMainAction));
        } else if (isBlockAction(actionToPerform)) {
            BlockAction blockAction = (BlockAction) actionToPerform;
            BlockAction newBlockAction = BlockAction.block(newPlayer, blockAction.getActionType(), blockAction.getBlockAs());

            return WaitingChallengeBlockActionState.of(newPlayers, newPlayer, getDeck(), getMainAction(), newBlockAction, newActionHistory, getTarget().orElse(null), newPlayers.others(newPlayer).get());
        }

        return WaitingToPerformActionsState.of(newPlayers, newPlayer, getDeck(), getMainAction(), newActionHistory, newTarget.orElse(null))
                .performAction(PerformAction.takeCoinsForAssassination(newPlayer));
    }

    private MainAction newMainAction(Player player, Optional<Player> target) {
        if (target.isPresent()) {
            return MainAction.of(player, getMainAction().getActionType(), target.get());
        }

        return MainAction.of(player, getMainAction().getActionType());
    }

    private Optional<Player> newTarget(Player newRevealer) {
        return getTarget().map(target -> {
            if (target.equals(getRevealer())) {
                return newRevealer;
            }
            return target;
        });
    }

    private Player newPlayer(Player newRevealer) {
        if (getPlayer().equals(getRevealer())) {
            return newRevealer;
        }

        return getPlayer();
    }

    @Override
    public List<RevealCardAction> getAllowableActions() {
        return revealer.getActiveCardSet()
                .stream()
                .map(c -> RevealCardAction.of(revealer, c))
                .collect(Collectors.toList());
    }

    public Player getRevealer() {
        return revealer;
    }


}
