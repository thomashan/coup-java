package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.PutBackCardsAction;
import com.thomashan.coup.player.PlayerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.thomashan.coup.card.Card.AMBASSADOR;
import static com.thomashan.coup.card.Card.CAPTAIN;
import static com.thomashan.coup.card.Card.DUKE;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class WaitingPutBackCardsActionStateTest extends WaitingPutBackCardsActionStateTestCase {
    @BeforeEach
    public void setUp() {
        setUpPlayer(PlayerBuilder.newBuilder().cards(AMBASSADOR, AMBASSADOR, CAPTAIN, DUKE));
    }

    @Test
    public void testGetAllowableActions() {
        WaitingPutBackCardsActionState waitingPutBackCardsActionState = createWaitingPutBackCardsActionState();
        List<PutBackCardsAction> expected = Arrays.asList(
                PutBackCardsAction.select(getPlayer(), AMBASSADOR, AMBASSADOR),
                PutBackCardsAction.select(getPlayer(), AMBASSADOR, CAPTAIN),
                PutBackCardsAction.select(getPlayer(), AMBASSADOR, DUKE),
                PutBackCardsAction.select(getPlayer(), CAPTAIN, DUKE),
                PutBackCardsAction.putBack(getPlayer(), AMBASSADOR, AMBASSADOR),
                PutBackCardsAction.putBack(getPlayer(), AMBASSADOR, CAPTAIN),
                PutBackCardsAction.putBack(getPlayer(), AMBASSADOR, DUKE),
                PutBackCardsAction.putBack(getPlayer(), CAPTAIN, DUKE)
        );

        assertIterableEquals(expected, waitingPutBackCardsActionState.getAllowableActions());
    }
}
