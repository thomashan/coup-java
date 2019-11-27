package com.thomashan.coup.turn.state;

import com.thomashan.coup.Deck;
import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.ChallengeActionType;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.PutBackCardsAction;

import java.util.List;
import java.util.Optional;

public class WaitingPutBackCardsActionState implements TurnState<PutBackCardsAction> {
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
        return null;
    }

    @Override
    public Optional<Player> getTarget() {
        return Optional.empty();
    }

    @Override
    public Optional<ChallengeActionType> getChallengeActionType() {
        return Optional.empty();
    }

    @Override
    public Optional<Player> getMainActionChallengedBy() {
        return Optional.empty();
    }

    @Override
    public Optional<BlockActionType> getBlockAction() {
        return Optional.empty();
    }

    @Override
    public Optional<ChallengeActionType> getBlockChallengeActionType() {
        return Optional.empty();
    }

    @Override
    public Optional<Player> getBlockActionChallengedBy() {
        return Optional.empty();
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return null;
    }

    @Override
    public TurnState performAction(PutBackCardsAction action) {
        return null;
    }
}
