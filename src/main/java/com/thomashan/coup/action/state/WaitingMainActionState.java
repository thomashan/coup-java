package com.thomashan.coup.action.state;

import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.coup.Deck;
import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.ChallengeActionType;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.MainActionType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;

public final class WaitingMainActionState implements ActionState<MainAction> {
    private final Players players;
    private final Player player;
    private final ImmutableList<Action> actionHistory;

    private WaitingMainActionState(Players players, Player player) {
        this.players = players;
        this.player = player;
        this.actionHistory = ImmutableList.of();
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
        return Collections.singletonList(player);
    }

    @Override
    public Optional<Player> getTarget() {
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
    public Optional<Player> getMainActionChallengedBy() {
        return empty();
    }

    @Override
    public Optional<Player> getBlockActionChallengedBy() {
        return empty();
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public ActionState performAction(MainAction action) {
        checkActionPlayerIsSameAsStatePlayer(action);
        checkActionAllowable(action);

        ImmutableList<Action> newActionHistory = actionHistory.plus(action);
        MainActionType mainActionType = action.getActionType();

        if (!mainActionType.isChallengeable()) {
            switch (mainActionType) {
                case INCOME:
                    Player newPlayer = player.income();
                    Players newPlayers = players.updatePlayer(player, newPlayer);

                    return CompletedState.of(newPlayers, newPlayer, newActionHistory);
                case COUP: {
                    return action.getTarget()
                            .map(target -> WaitingRevealCardState.of(players, player, newActionHistory, target, target))
                            .orElseThrow(() -> new IllegalArgumentException("Coup must specify target"));
                }

                default:
                    throw new IllegalArgumentException("Unexpected non-challengeable acton");
            }
        }

        return WaitingChallengeMainActionState.of(players, player, newActionHistory, action.getTarget().orElse(null));
    }

    private void checkActionAllowable(MainAction action) {
        if (!action.getActionType().isAllowable(player.getCoins())) {
            throw new IllegalArgumentException("This action is not allowable");
        }
    }

    private void checkActionPlayerIsSameAsStatePlayer(MainAction action) {
        if (!player.equals(action.getPlayer())) {
            throw new IllegalArgumentException("Trying to perform action with different player");
        }
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return Arrays.stream(MainActionType.values())
                .filter(mainActionType -> mainActionType.isAllowable(player.getCoins()))
                .collect(Collectors.toList());
    }

    public static WaitingMainActionState of(Players players, Player player) {
        return new WaitingMainActionState(players, player);
    }
}
