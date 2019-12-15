package com.thomashan.coup.action;

import com.thomashan.collection.immutable.ImmutableSet;
import com.thomashan.coup.card.Card;
import com.thomashan.coup.player.Player;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.thomashan.coup.action.PutBackCardActionType.PUT_BACK;
import static com.thomashan.coup.action.PutBackCardActionType.SELECT;
import static java.util.Optional.empty;

@EqualsAndHashCode
public class PutBackCardsAction implements Action<PutBackCardActionType> {
    private final Player player;
    private final PutBackCardActionType putBackCardActionType;
    private final int numberOfCards;
    private final Set<Card> cards;

    private PutBackCardsAction(Player player, PutBackCardActionType putBackCardActionType, List<Card> cards) {
        this.player = player;
        this.putBackCardActionType = putBackCardActionType;
        this.numberOfCards = cards.size();
        this.cards = ImmutableSet.of(cards);
    }

    public static PutBackCardsAction select(Player player, Card... selectedCards) {
        checkNumberOfCardsSelected(selectedCards);
        return new PutBackCardsAction(player, SELECT, Arrays.asList(selectedCards));
    }

    public static PutBackCardsAction putBack(Player player, Card card1, Card card2) {
        return new PutBackCardsAction(player, PUT_BACK, Arrays.asList(card1, card2));
    }

    private static void checkNumberOfCardsSelected(Card... cards) {
        if (cards.length > 2) {
            throw new IllegalArgumentException("You cannot select more than two cards");
        }
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public PutBackCardActionType getActionType() {
        return putBackCardActionType;
    }

    @Override
    public Optional<Player> getTarget() {
        return empty();
    }

    @Override
    public boolean isCheckForActivePlayer() {
        return true;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public Set<Card> getCards() {
        return cards;
    }
}
