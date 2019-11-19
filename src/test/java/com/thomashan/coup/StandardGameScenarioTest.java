package com.thomashan.coup;

import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.MainActionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class StandardGameScenarioTest {
    @Test
    public void testTwoPlayerScenario_ShouldPass() {
        Game game = StandardGame.create(2);
        Player player1 = game.getPlayers().get().get(0);
        Player player2 = game.getPlayers().get().get(1);

        Game game1 = game.action(MainAction.of(player1, MainActionType.INCOME));
        Game game2 = game1.action(MainAction.of(player2, MainActionType.INCOME));

        assertFalse(game1.equals(game2));
    }
}
