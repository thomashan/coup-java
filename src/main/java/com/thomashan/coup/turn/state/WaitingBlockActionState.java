package com.thomashan.coup.turn.state;

import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.BlockAction;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.PerformAction;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.thomashan.coup.action.BlockActionType.NO_BLOCK;
import static java.util.Optional.empty;

public final class WaitingBlockActionState implements TurnState<BlockAction> {
    private final Players players;
    private final Player player;
    private final Deck deck;
    private final MainAction mainAction;
    private final ImmutableList<Action> actionHistory;
    private final Optional<Player> target;

    private WaitingBlockActionState(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Player target) {
        this.players = players;
        this.player = player;
        this.deck = deck;
        this.mainAction = mainAction;
        this.actionHistory = ImmutableList.of(actionHistory);

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    public static WaitingBlockActionState of(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Player target) {
        return new WaitingBlockActionState(players, player, deck, mainAction, actionHistory, target);
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
    public TurnState performAction(BlockAction action) {
        checkBlockActionMatchesMainAction(action);

        ImmutableList<Action> newActionHistory = actionHistory.plus(action);

        switch (action.getActionType()) {
            case NO_BLOCK:
                PerformAction performAction = PerformAction.mainAction(getMainAction());
                return WaitingToPerformActionsState.of(getPlayers(), getPlayer(), getDeck(), getMainAction(), newActionHistory, getTarget().orElse(null))
                        .performAction(performAction);
            default:
                return WaitingChallengeBlockActionState.of(getPlayers(), getPlayer(), getDeck(), getMainAction(), action, newActionHistory, getTarget().orElse(null), getPlayers().others(action.getPlayer()).get());
        }
    }

    private void checkBlockActionMatchesMainAction(BlockAction action) {
        BlockActionType blockActionType = getBlockActionType();

        if (action.getActionType() != blockActionType && action.getActionType() != NO_BLOCK) {
            throw new IllegalArgumentException("Your block action is incompatible with the main action");
        }
    }

    @Override
    public List<BlockAction> getAllowableActions() {
        BlockActionType blockActionType = getBlockActionType();

        Player target = getMainAction().getTarget().orElseThrow(() -> new IllegalStateException("The main action didn't specify a target"));

        return Stream.concat(
                blockActionType
                        .getBlockableBy()
                        .stream()
                        .map(c -> BlockAction.block(target, blockActionType, c)),
                Stream.of(BlockAction.noBlock(target)))
                .collect(Collectors.toList());
    }

    private BlockActionType getBlockActionType() {
        return getMainAction()
                .getActionType()
                .getBlockAction()
                .orElseThrow(() -> new IllegalStateException("Main action is not blockable"));
    }
}
