package com.thomashan.coup;

import com.thomashan.coup.action.MainAction;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.action.MainActionType.INCOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StandardGameTest {
    @Test
    public void testCreate_DrawCorrectNumberOfCards() {
        assertEquals(11, StandardGame.create(2).getDeck().getNumberOfCards());
    }

    @Test
    public void testCreate_CreateCorrectNumberOfPlayers() {
        assertEquals(2, StandardGame.create(2).getPlayers().getNumberOfPlayers());
    }

    @Test
    public void testCreate_MinimumNumberOfPlayersException() {
        assertThrows(IllegalArgumentException.class, () -> StandardGame.create(1));
    }

    @Test
    public void testCreate_MaximumNumberOfPlayersException() {
        assertThrows(IllegalArgumentException.class, () -> StandardGame.create(7));
    }

    @Test
    public void testGetCurrentPlayer_GivenFirstTurn_ReturnsFirstPlayer() {
        Game game = StandardGame.create(2);
        Players players = game.getPlayers();

        assertEquals(players.get().get(0), game.getCurrentPlayer());
    }

    @Test
    public void testGetCurrentPlayer_GivenSecondTurn_ReturnsSecondPlayer() {
        Game game = StandardGame.create(2);
        game = game.action(MainAction.of(game.getCurrentPlayer(), INCOME));
        Players players = game.getPlayers();

        assertEquals(players.get().get(1), game.getCurrentPlayer());
    }

    @Test
    public void testGetTurn_GivenFirstTurn_Returns0() {
        Game game = StandardGame.create(2);

        assertEquals(0, game.getTurn().getTurnNumber());
    }

    @Test
    public void testGetTurn_GivenSecondTurn_Returns() {
        Game game = StandardGame.create(2);
        game = game.action(MainAction.of(game.getCurrentPlayer(), INCOME));

        assertEquals(1, game.getTurn().getTurnNumber());
    }
}
