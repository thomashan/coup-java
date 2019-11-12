package com.thomashan.coup;

import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.MainActionType;
import org.junit.jupiter.api.Test;

public class StandardGameScenarioTest {
    @Test
    public void testTwoPlayerScenario_ShouldPass() {
        StandardGame game = StandardGame.create(2);
        Player player1 = game.getPlayers().get().get(0);
        Player player2 = game.getPlayers().get().get(1);

        game.action(MainAction.of(player1, MainActionType.INCOME));
    }
}
