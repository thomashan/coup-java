package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.MainActionType;
import com.thomashan.coup.action.NoAction;
import com.thomashan.coup.action.PerformAction;
import com.thomashan.coup.action.PerformActionType;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.card.DrawnCard;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

public class WaitingToPerformActionsState implements TurnState<PerformAction> {
    private final Players players;
    private final Player player;
    private final Deck deck;
    private final MainAction mainAction;
    private final List<Action> actionHistory;
    private final Optional<Player> target;

    private WaitingToPerformActionsState(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Player target) {
        this.players = players;
        this.player = player;
        this.deck = deck;
        this.mainAction = mainAction;
        this.actionHistory = actionHistory;

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    static WaitingToPerformActionsState of(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Player target) {
        return new WaitingToPerformActionsState(players, player, deck, mainAction, actionHistory, target);
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
        return false;
    }

    @Override
    public List<PerformAction> getAllowableActions() {
        return Collections.emptyList();
    }

    @Override
    public TurnState performAction(PerformAction action) {
        if (action.getActionType() instanceof MainActionType) {
            MainActionType mainActionType = (MainActionType) action.getActionType();

            switch (mainActionType) {
                case ASSASSINATE: {
                    Player newPlayer = getPlayer().useCoinsForAssassination();
                    Players newPlayers = getPlayers().updatePlayer(getPlayer(), newPlayer);

                    return action.getTarget()
                            .map(target -> WaitingRevealCardState.of(newPlayers, newPlayer, getDeck(), getMainAction(), getActionHistory(), NoAction.get(), target, target))
                            .orElseThrow(() -> new IllegalArgumentException("Assassinate must specify target"));
                }
                case TAX: {
                    Player newPlayer = action.getPlayer().tax();
                    Players newPlayers = getPlayers().updatePlayer(getPlayer(), newPlayer);

                    return CompletedState.of(newPlayers, newPlayer, getDeck(), getMainAction(), getActionHistory(), null);
                }
                case STEAL: {
                    Player target = action.getTarget()
                            .orElseThrow(() -> new IllegalArgumentException("Steal must specify target"));
                    Player newTarget = target
                            .stolenToOtherPlayer();
                    Player newPlayer = getPlayer()
                            .stealFromOtherPlayer(target.getCoins());
                    Players newPlayers = getPlayers()
                            .updatePlayer(getPlayer(), newPlayer)
                            .updatePlayer(target, newTarget);

                    return CompletedState.of(newPlayers, newPlayer, getDeck(), getMainAction(), getActionHistory(), getTarget().orElse(null));
                }
                case EXCHANGE: {
                    DrawnCard drawnCard = getDeck().draw();
                    Player newPlayer = getPlayer().plus(drawnCard.getCard());
                    drawnCard = drawnCard.getDeck().draw();
                    newPlayer = newPlayer.plus(drawnCard.getCard());
                    Deck newDeck = drawnCard.getDeck();
                    Players newPlayers = getPlayers().updatePlayer(getPlayer(), newPlayer);

                    return WaitingPutBackCardsActionState.of(newPlayers, newPlayer, newDeck, getMainAction(), getActionHistory(), null);
                }
            }
        } else if (action.getActionType() instanceof PerformActionType) {
            switch ((PerformActionType) action.getActionType()) {
                default: {
                    Player newPlayer = action.getPlayer().useCoinsForAssassination();
                    Players newPlayers = getPlayers().updatePlayer(action.getPlayer(), newPlayer);

                    return CompletedState.of(newPlayers, newPlayer, getDeck(), getMainAction(), getActionHistory(), getTarget().orElse(null));
                }
            }
        }

        return null;
    }
}
