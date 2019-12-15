package com.thomashan.coup.turn.state;

import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionDetector;
import com.thomashan.coup.action.BlockAction;
import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;

import java.util.List;

import static com.thomashan.coup.turn.state.WaitingChallengeActionState.FromState.BLOCK;

public class WaitingChallengeBlockActionState extends WaitingChallengeActionState {
    private final BlockAction blockAction;

    private WaitingChallengeBlockActionState(Players players, Player player, Deck deck, MainAction mainAction, BlockAction blockAction, List<Action> actionHistory, Player target, List<Player> challengeablePlayers) {
        super(players, player, deck, mainAction, actionHistory, target, challengeablePlayers, BLOCK);
        this.blockAction = blockAction;
    }

    public static WaitingChallengeBlockActionState of(Players players, Player player, Deck deck, MainAction mainAction, BlockAction blockAction, List<Action> actionHistory, Player target, List<Player> challengeablePlayers) {
        return new WaitingChallengeBlockActionState(players, player, deck, mainAction, blockAction, actionHistory, target, challengeablePlayers);
    }

    @Override
    protected TurnState toWaitingChallengeActionState(ChallengeAction action, List<Action> actionHistory) {
        return WaitingChallengeBlockActionState.of(getPlayers(), getPlayer(), getDeck(), getMainAction(), getBlockAction(), actionHistory, getTarget().orElse(null), ImmutableList.of(getChallengeablePlayers()).minus(action.getPlayer()));
    }

    @Override
    protected TurnState toRevealCardState(ChallengeAction action, List<Action> actionHistory) {
        if (ActionDetector.isBluff(blockAction.getActionType(), blockAction.getPlayer().getActiveCardSet())) {
            return WaitingRevealCardState.of(getPlayers(), getPlayer(), getDeck(), getMainAction(), actionHistory, getMainAction(), getTarget().orElse(null), blockAction.getPlayer());
        } else {
            return WaitingRevealCardState.of(getPlayers(), getPlayer(), getDeck(), getMainAction(), actionHistory, blockAction, getTarget().orElse(null), action.getPlayer());
        }
    }

    @Override
    protected void checkAction(ChallengeAction action) {
        // FIXME: put in action checks here
    }

    public BlockAction getBlockAction() {
        return blockAction;
    }
}
