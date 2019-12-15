package com.thomashan.coup.turn.state;

import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.PutBackCardsAction;
import com.thomashan.coup.card.Card;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public class WaitingPutBackCardsActionState implements TurnState<PutBackCardsAction> {
    private final Players players;
    private final Player player;
    private final MainAction mainAction;
    private final Deck deck;
    private final ImmutableList<Action> actionHistory;
    private final Optional<Player> target;

    private WaitingPutBackCardsActionState(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Player target) {
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

    static WaitingPutBackCardsActionState of(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Player target) {
        return new WaitingPutBackCardsActionState(players, player, deck, mainAction, actionHistory, target);
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
    public TurnState performAction(PutBackCardsAction action) {
        ImmutableList<Action> newActionHistory = actionHistory.plus(action);

        List<Card> cardsToPutBack = cardsToPutBack(action);
        Player newPlayer = action.getPlayer();
        Deck newDeck = getDeck();

        for (Card cardToPutBack : cardsToPutBack) {
            newPlayer = newPlayer.minus(cardToPutBack);
            newDeck = newDeck.plus(cardToPutBack);
        }

        Players newPlayers = getPlayers().updatePlayer(action.getPlayer(), newPlayer);

        return CompletedState.of(newPlayers, newPlayer, newDeck, getMainAction(), newActionHistory, getTarget().orElse(null));
    }

    private List<Card> cardsToPutBack(PutBackCardsAction putBackCardsAction) {
        switch (putBackCardsAction.getActionType()) {
            case SELECT: {
                List<Card> cardsToPutBack = new ArrayList<>(putBackCardsAction.getPlayer().getActiveCards());
                List<Card> cardsSelected = expandCards(putBackCardsAction);
                for (Card cardSelected : cardsSelected) {
                    cardsToPutBack.remove(cardSelected);
                }

                return ImmutableList.of(cardsToPutBack);
            }
            case PUT_BACK: {
                List<Card> cardsToPutBack = expandCards(putBackCardsAction);

                return ImmutableList.of(cardsToPutBack);
            }
            default:
                throw new IllegalArgumentException("Unknown put back card action type");
        }
    }

    private List<Card> expandCards(PutBackCardsAction putBackCardsAction) {
        if (putBackCardsAction.getCards().size() != putBackCardsAction.getNumberOfCards()) {
            Card cardToSelect = putBackCardsAction.getCards().iterator().next();
            return ImmutableList.of(cardToSelect, cardToSelect);
        }

        return ImmutableList.of(putBackCardsAction.getCards());
    }

    @Override
    public List<PutBackCardsAction> getAllowableActions() {
        Stream<PutBackCardsAction> selectCardsActions = selectCardsActions();
        Stream<PutBackCardsAction> putBackCardsActions = putBackCardsActions();

        return Stream.concat(selectCardsActions, putBackCardsActions)
                .collect(Collectors.toList());
    }

    private Stream<PutBackCardsAction> putBackCardsActions() {
        return getPlayer().getActiveCards().stream()
                .flatMap(card1 -> {
                    ImmutableList<Card> pickSecondCardsFrom = ImmutableList.of(getPlayer().getActiveCards()).minus(card1);

                    return pickSecondCardsFrom.stream()
                            .map(card2 -> PutBackCardsAction.putBack(getPlayer(), card1, card2));
                })
                .distinct();
    }

    private Stream<PutBackCardsAction> selectCardsActions() {
        if (getPlayer().getActiveCards().size() == 3) {
            return getPlayer().getActiveCardSet().stream()
                    .map(card -> PutBackCardsAction.select(getPlayer(), card))
                    .distinct();
        }

        return getPlayer().getActiveCards().stream()
                .flatMap(card1 -> {
                    ImmutableList<Card> pickSecondCardsFrom = ImmutableList.of(getPlayer().getActiveCards()).minus(card1);

                    return pickSecondCardsFrom.stream()
                            .map(card2 -> PutBackCardsAction.select(getPlayer(), card1, card2));
                })
                .distinct();
    }
}
