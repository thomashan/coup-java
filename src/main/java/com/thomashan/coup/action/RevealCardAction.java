package com.thomashan.coup.action;

import com.thomashan.coup.card.Card;
import com.thomashan.coup.player.Player;
import lombok.EqualsAndHashCode;

import java.util.Optional;

import static com.thomashan.coup.action.RevealCardActionType.REVEAL;
import static java.util.Optional.empty;

@EqualsAndHashCode
public final class RevealCardAction implements Action<RevealCardActionType> {
    private final Player player;
    private final Card card;

    private RevealCardAction(Player player, Card card) {
        this.player = player;
        this.card = card;
    }

    public static RevealCardAction of(Player player, Card card) {
        return new RevealCardAction(player, card);
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
    public RevealCardActionType getActionType() {
        return REVEAL;
    }

    @Override
    public boolean isCheckForActivePlayer() {
        return true;
    }

    public Card getCard() {
        return card;
    }
}
