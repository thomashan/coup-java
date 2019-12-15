package com.thomashan.coup.turn.state;

import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionDetector;
import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.NoAction;
import com.thomashan.coup.action.PerformAction;
import com.thomashan.coup.card.Card;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.card.DrawnCard;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;

import java.util.List;

import static com.thomashan.coup.turn.state.WaitingChallengeActionState.FromState.MAIN;

public final class WaitingChallengeMainActionState extends WaitingChallengeActionState {

    private WaitingChallengeMainActionState(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Player target, List<Player> challengeablePlayers) {
        super(players, player, deck, mainAction, actionHistory, target, challengeablePlayers, MAIN);
    }

    public static WaitingChallengeMainActionState of(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Player target, List<Player> challengeablePlayers) {
        return new WaitingChallengeMainActionState(players, player, deck, mainAction, actionHistory, target, challengeablePlayers);
    }

    @Override
    protected TurnState toRevealCardState(ChallengeAction action, List<Action> actionHistory) {
        Player newPlayer = getPlayer();
        Players newPlayers = getPlayers();

        if (isMainActionPlayerBluffing()) {
            switch (getMainAction().getActionType()) {
                case ASSASSINATE:
                    return WaitingRevealCardState.of(newPlayers, newPlayer, getDeck(), getMainAction(), actionHistory, PerformAction.takeCoinsForAssassination(newPlayer), getTarget().orElse(null), newPlayer);
                default:
                    return WaitingRevealCardState.of(newPlayers, newPlayer, getDeck(), getMainAction(), actionHistory, NoAction.get(), getTarget().orElse(null), newPlayer);
            }
        } else {
            Card card = ActionDetector.getActionableCard(getMainAction().getActionType());
            newPlayer = newPlayer.minus(card);
            DrawnCard drawnCard = getDeck().plus(card).shuffle().draw();
            newPlayer = newPlayer.plus(drawnCard.getCard());
            newPlayers = newPlayers.updatePlayer(getPlayer(), newPlayer);

            return WaitingRevealCardState.of(newPlayers, newPlayer, drawnCard.getDeck(), getMainAction(), actionHistory, getMainAction(), getTarget().orElse(null), action.getPlayer());
        }
    }

    private boolean isMainActionPlayerBluffing() {
        return ActionDetector.isBluff(getMainAction().getActionType(), getMainAction().getPlayer().getActiveCardSet());
    }

    @Override
    protected TurnState toWaitingChallengeActionState(ChallengeAction action, List<Action> actionHistory) {
        return WaitingChallengeMainActionState.of(getPlayers(), getPlayer(), getDeck(), getMainAction(), actionHistory, getTarget().orElse(null), ImmutableList.of(getChallengeablePlayers()).minus(action.getPlayer()));
    }

    @Override
    protected void checkAction(ChallengeAction action) {
        checkMainActionIsChallengeable();
        checkActionPlayerIsDifferentToMainActionPlayer(action);
    }

    private void checkActionPlayerIsDifferentToMainActionPlayer(ChallengeAction action) {
        if (getMainAction().getPlayer().equals(action.getPlayer())) {
            throw new IllegalArgumentException("You cannot challenge your own action");
        }
    }

    private void checkMainActionIsChallengeable() {
        if (!getMainAction().getActionType().isChallengeable()) {
            throw new IllegalArgumentException("Challenging a non-challengeable action");
        }
    }
}
