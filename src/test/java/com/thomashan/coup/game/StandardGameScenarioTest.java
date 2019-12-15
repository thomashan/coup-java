package com.thomashan.coup.game;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.BlockAction;
import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.action.RevealCardAction;
import com.thomashan.coup.player.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.thomashan.coup.action.BlockActionType.BLOCK_ASSASSINATE;
import static com.thomashan.coup.action.ChallengeActionType.ALLOW;
import static com.thomashan.coup.action.ChallengeActionType.CHALLENGE;
import static com.thomashan.coup.action.MainActionType.ASSASSINATE;
import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.card.Card.AMBASSADOR;
import static com.thomashan.coup.card.Card.ASSASSIN;
import static com.thomashan.coup.card.Card.CAPTAIN;
import static com.thomashan.coup.card.Card.CONTESSA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StandardGameScenarioTest {
    @Test
    public void test_AssassinateChallenger_Challenge_ChallengeSuccess() {
        Game game = SetupGame.create(
                Player.of(CAPTAIN, AMBASSADOR).income(),
                Player.of(CAPTAIN, AMBASSADOR));

        game = game.action(MainAction.of(game.getCurrentPlayer(), ASSASSINATE, game.getPlayers().others(game.getCurrentPlayer()).get(0)));
        game = game.action(ChallengeAction.of(game.getPlayers().others(game.getCurrentPlayer()).get(0), CHALLENGE));
        game = game.action(RevealCardAction.of(game.getCurrentPlayer(), AMBASSADOR));

        assertEquals(1, game.getTurn().getTurnNumber());
        assertEquals(0, game.getPlayers().get(0).getCoins());
        assertEquals(1, getOtherPlayer(game).getActiveCardSet().size());
    }

    @Test
    public void test_AssassinateChallenger_Challenge_ChallengeFail_ShouldMakeChallengerInactive() {
        Game game = SetupGame.create(
                Player.of(ASSASSIN, AMBASSADOR).income(),
                Player.of(CAPTAIN, AMBASSADOR));

        game = game.action(MainAction.of(game.getCurrentPlayer(), ASSASSINATE, getOtherPlayer(game)));
        game = game.action(ChallengeAction.of(getOtherPlayer(game), CHALLENGE));
        game = game.action(RevealCardAction.of(getOtherPlayer(game), AMBASSADOR));
        game = game.action(BlockAction.noBlock(getOtherPlayer(game)));
        game = game.action(RevealCardAction.of(getOtherPlayer(game), CAPTAIN));

        assertEquals(1, game.getTurn().getTurnNumber());
        assertEquals(0, game.getPlayers().get(0).getCoins());
        assertTrue(game.isComplete());
    }

    @Test
    public void test_AssassinateChallenger_Challenge_ChallengeFail_ChallengerBlock_ChallengeBlock_ShouldMakeChallengerInactive() {
        Game game = SetupGame.create(
                Player.of(ASSASSIN, AMBASSADOR).income(),
                Player.of(CAPTAIN, AMBASSADOR));

        game = game.action(MainAction.of(game.getCurrentPlayer(), ASSASSINATE, getOtherPlayer(game)));
        game = game.action(ChallengeAction.of(getOtherPlayer(game), CHALLENGE));
        game = game.action(RevealCardAction.of(getOtherPlayer(game), AMBASSADOR));
        game = game.action(BlockAction.block(getOtherPlayer(game), BLOCK_ASSASSINATE, CONTESSA));
        game = game.action(ChallengeAction.of(game.getCurrentPlayer(), CHALLENGE));
        game = game.action(RevealCardAction.of(getOtherPlayer(game), CAPTAIN));

        assertEquals(1, game.getTurn().getTurnNumber());
        assertEquals(0, game.getPlayers().get(0).getCoins());
        assertTrue(game.isComplete());
    }

    @Test
    public void test_AssassinateChallenger_Allow_ChallengerBlock_AllowBlock_ShouldGoToNextTurn() {
        Game game = SetupGame.create(
                Player.of(ASSASSIN, AMBASSADOR).income(),
                Player.of(CAPTAIN, AMBASSADOR));

        game = game.action(MainAction.of(game.getCurrentPlayer(), ASSASSINATE, getOtherPlayer(game)));
        game = game.action(ChallengeAction.of(getOtherPlayer(game), ALLOW));
        game = game.action(BlockAction.block(getOtherPlayer(game), BLOCK_ASSASSINATE, CONTESSA));
        game = game.action(ChallengeAction.of(game.getCurrentPlayer(), ALLOW));

        assertEquals(1, game.getTurn().getTurnNumber());
        assertEquals(0, game.getPlayers().get(0).getCoins());
        assertFalse(game.isComplete());
    }

    private Player getOtherPlayer(Game game) {
        return game.getPlayers().others(game.getCurrentPlayer()).get(0);
    }

    @Test
    public void test_Exchange_Challenge_ChallengeFail_PlayerShouldHaveTwoNewCards() {
        Game game = SetupGame.create(
                Player.of(AMBASSADOR, AMBASSADOR),
                Player.of(CAPTAIN, AMBASSADOR));

        game = game.action(MainAction.of(game.getCurrentPlayer(), EXCHANGE));
        game = game.action(ChallengeAction.of(getOtherPlayer(game), CHALLENGE));
        game = game.action(RevealCardAction.of(getOtherPlayer(game), AMBASSADOR));
        game = game.action(game.getAllowableActions().get(0));

        assertEquals(1, game.getTurn().getTurnNumber());
        assertEquals(2, game.getPlayers().get(0).getCoins());
        assertEquals(2, game.getPlayers().get(0).getActiveCards().size());
    }

    @Disabled("deal with infinitely doable action sequences")
    @Test
    public void test_FullGame() {
        Game game = SetupGame.create(
                Player.of(AMBASSADOR, AMBASSADOR),
                Player.of(CAPTAIN, AMBASSADOR));

        performAction(game);
        System.out.println("");
    }

    private void performAction(Game game) {
        while (!game.isComplete()) {
            List<Action> allowableActions = game.getAllowableActions();
            for (Action allowableAction : allowableActions) {
                Game newGame = game.action(allowableAction);
                performAction(newGame);
            }
        }
    }
}
