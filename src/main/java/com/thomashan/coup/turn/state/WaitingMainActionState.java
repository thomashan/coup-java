package com.thomashan.coup.turn.state;

import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.MainActionType;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class WaitingMainActionState implements TurnState<MainAction> {
    private final Players players;
    private final Player player;
    private final Deck deck;
    private final ImmutableList<Action> actionHistory;

    private WaitingMainActionState(Players players, Player player, Deck deck) {
        this.players = players;
        this.player = player;
        this.deck = deck;
        this.actionHistory = ImmutableList.of();
    }

    public static WaitingMainActionState of(Players players, Player player, Deck deck) {
        return new WaitingMainActionState(players, player, deck);
    }

    @Override
    public MainAction getMainAction() {
        throw new UnsupportedOperationException("Waiting for main action to be performed");
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
        return empty();
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
    public TurnState performAction(MainAction action) {
        checkActionPlayerIsSameAsStatePlayer(action);

        ImmutableList<Action> newActionHistory = actionHistory.plus(action);
        MainActionType mainActionType = action.getActionType();

        if (!mainActionType.isChallengeable()) {
            switch (mainActionType) {
                case INCOME:
                    Player newPlayer = getPlayer().income();
                    Players newPlayers = getPlayers().updatePlayer(getPlayer(), newPlayer);

                    return CompletedState.of(newPlayers, newPlayer, getDeck(), action, newActionHistory, null);
                case FOREIGN_AID:
                    return WaitingBlockActionState.of(getPlayers(), getPlayer(), getDeck(), action, newActionHistory, getTarget().orElse(null));
                case COUP:
                    return action.getTarget()
                            .map(target -> WaitingRevealCardState.of(getPlayers(), getPlayer(), getDeck(), action, newActionHistory, action, target, target))
                            .orElseThrow(() -> new IllegalArgumentException("Coup must specify target"));
                default:
                    throw new IllegalArgumentException("Unexpected non-challengeable acton");
            }
        }

        return WaitingChallengeMainActionState.of(getPlayers(), getPlayer(), getDeck(), action, newActionHistory, action.getTarget().orElse(null), getPlayers().others(getPlayer()).get());
    }

    private void checkActionPlayerIsSameAsStatePlayer(MainAction action) {
        if (!player.equals(action.getPlayer())) {
            throw new IllegalArgumentException("Trying to perform action with different player");
        }
    }

    @Override
    public List<MainAction> getAllowableActions() {
        return Arrays.stream(MainActionType.values())
                .filter(mainActionType -> mainActionType.isAllowable(player.getCoins()))
                .flatMap(a -> {
                    if (a.isRequiresTarget()) {
                        return players.others(player).get().stream()
                                .map(t -> MainAction.of(player, a, t));
                    }

                    return Stream.of(MainAction.of(player, a));
                })
                .collect(Collectors.toList());
    }
}
