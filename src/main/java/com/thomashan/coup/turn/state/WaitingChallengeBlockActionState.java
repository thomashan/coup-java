package com.thomashan.coup.turn.state;

import com.thomashan.coup.Deck;
import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.ChallengeActionType;
import com.thomashan.coup.action.MainAction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

public class WaitingChallengeBlockActionState implements TurnState<ChallengeAction> {
    @Override
    public MainAction getMainAction() {
        return null;
    }

    @Override
    public List<Action> getActionHistory() {
        return null;
    }

    @Override
    public Deck getDeck() {
        return null;
    }

    @Override
    public Players getPlayers() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public List<Player> getActionablePlayers() {
        // FIXME: return all player apart from the player that performed the block
        return null;
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
    public WaitingRevealCardState performAction(ChallengeAction action) {
        return null;
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return Arrays.asList(ChallengeActionType.values());
    }
}
