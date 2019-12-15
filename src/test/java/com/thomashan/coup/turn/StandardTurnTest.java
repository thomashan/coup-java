package com.thomashan.coup.turn;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.BlockAction;
import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.card.StandardDeck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.thomashan.coup.action.BlockActionType.BLOCK_STEAL;
import static com.thomashan.coup.action.ChallengeActionType.ALLOW;
import static com.thomashan.coup.action.ChallengeActionType.CHALLENGE;
import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.FOREIGN_AID;
import static com.thomashan.coup.action.MainActionType.INCOME;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.action.MainActionType.TAX;
import static com.thomashan.coup.card.Card.AMBASSADOR;
import static com.thomashan.coup.card.Card.CAPTAIN;
import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


// FIXME: revisit all tests for getActionablePlayers and getAllowableActions logic
class StandardTurnTest {
    private Player player;
    private Players players;
    private Deck deck;
    private Turn turn;

    @BeforeEach
    public void setUp() {
        this.player = newBuilder().build();
        this.players = Players.create(player, newBuilder().build(), newBuilder().build());
        this.deck = StandardDeck.create();
        this.turn = StandardTurn.create(players, deck);
    }

    @Test
    public void testGetTurnNumber_GivenInitialTurn_ShouldReturn0() {
        assertEquals(0, turn.getTurnNumber());
    }

    @Test
    public void testIsComplete_GivenTurnStateIsNotComplete_ReturnsFalse() {
        assertFalse(turn.isComplete());
    }

    @Test
    public void testIsComplete_GivenTurnStateIsComplete_ReturnsTrue() {
        turn = turn.perform(MainAction.of(player, INCOME));

        assertTrue(turn.isComplete());
    }

    @Test
    public void testNewTurn_GivenTurnIsNotComplete_ThrowsException() {
        Throwable throwable = assertThrows(IllegalStateException.class, () -> turn = turn.newTurn());
        assertEquals("Can't generate a new turn when the turn state is not complete", throwable.getMessage());
    }

    @Test
    public void testNewTurn_GivenTurnIsComplete_ReturnsIncrementedTurnNumber() {
        turn = turn.perform(MainAction.of(player, INCOME)).newTurn();

        assertEquals(1, turn.getTurnNumber());
    }

    @Test
    public void testDeck() {
        assertEquals(deck, turn.getDeck());
    }

    @Test
    public void testPlayers() {
        assertEquals(players, turn.getPlayers());
    }

    @Test
    public void testPlayer() {
        assertEquals(player, turn.getPlayer());
    }

    @Test
    public void testPlayer_GivenNextTurn_ReturnsNextPlayer() {
        Player nextPlayer = players.get(1);
        turn = turn.perform(MainAction.of(player, INCOME)).newTurn();

        assertEquals(nextPlayer, turn.getPlayer());
    }

    @Test
    public void testGetActionHistory_GivenInitialTurn_ReturnsEmptyActionHistory() {
        assertIterableEquals(Collections.emptyList(), turn.getActionHistory());
    }

    @Test
    public void testGetActionHistory_GivenActionPerformed_ReturnsCorrectActionHistory() {
        Action action = MainAction.of(player, INCOME);
        turn = turn.perform(action);

        assertIterableEquals(Collections.singletonList(action), turn.getActionHistory());
    }

    @Test
    public void testGetAllowableActions_GivenInitialTurn_ReturnsCorrectActions() {
        Player player2 = players.get(1);
        Player player3 = players.get(2);
        List<Action> actions = turn.getAllowableActions();

        assertIterableEquals(Arrays.asList(
                MainAction.of(player, TAX),
                MainAction.of(player, STEAL, player2),
                MainAction.of(player, STEAL, player3),
                MainAction.of(player, EXCHANGE),
                MainAction.of(player, INCOME),
                MainAction.of(player, FOREIGN_AID)
        ), turn.getAllowableActions());
    }

    @Test
    public void testGetAllowableActions_GivenWaitingChallengeMainActionState_ReturnsCorrectActions() {
        Player player2 = players.get(1);
        Player player3 = players.get(2);
        turn = turn.perform(MainAction.of(player, STEAL, player2));

        assertIterableEquals(Arrays.asList(
                ChallengeAction.of(player2, ALLOW),
                ChallengeAction.of(player2, CHALLENGE),
                ChallengeAction.of(player3, ALLOW),
                ChallengeAction.of(player3, CHALLENGE)
        ), turn.getAllowableActions());
    }

    @Test
    public void testGetAllowableActions_GivenWaitingBlockActionState_ReturnsTarget() {
        Player player2 = players.get(1);
        Player player3 = players.get(2);
        turn = turn.perform(MainAction.of(player, STEAL, player2))
                .perform(ChallengeAction.of(player2, ALLOW))
                .perform(ChallengeAction.of(player3, ALLOW));

        assertIterableEquals(Arrays.asList(
                BlockAction.block(player2, BLOCK_STEAL, CAPTAIN),
                BlockAction.block(player2, BLOCK_STEAL, AMBASSADOR),
                BlockAction.noBlock(player2)
        ), turn.getAllowableActions());
    }

    @Test
    public void testGetAllowableActions_GivenWaitingChallengeBlockActionState_ReturnsCorrectActions() {
        Player player2 = players.get(1);
        Player player3 = players.get(2);
        turn = turn.perform(MainAction.of(player, STEAL, player2))
                .perform(ChallengeAction.of(player2, ALLOW))
                .perform(ChallengeAction.of(player3, ALLOW))
                .perform(BlockAction.block(player2, BLOCK_STEAL, CAPTAIN));

        List<Action> allowableActions = turn.getAllowableActions();

        assertIterableEquals(Arrays.asList(
                ChallengeAction.of(player, ALLOW),
                ChallengeAction.of(player, CHALLENGE),
                ChallengeAction.of(player3, ALLOW),
                ChallengeAction.of(player3, CHALLENGE)
        ), turn.getAllowableActions());
    }

    @Test
    public void testGetAllowableActions_GivenWaitingRevealCardState_ReturnsPlayerRevealingCard() {
        Player player2 = players.get(1);
        Player player3 = players.get(2);
        turn = turn.perform(MainAction.of(player, STEAL, player2));

        assertIterableEquals(Arrays.asList(
                ChallengeAction.of(player2, ALLOW),
                ChallengeAction.of(player2, CHALLENGE),
                ChallengeAction.of(player3, ALLOW),
                ChallengeAction.of(player3, CHALLENGE)
        ), turn.getAllowableActions());
    }

    @Test
    public void testGetAllowableActions_GivenCompletedState_ReturnsEmptyActions() {
        Player player2 = players.get(1);
        Player player3 = players.get(2);
        turn = turn.perform(MainAction.of(player, STEAL, player2));

        assertIterableEquals(Arrays.asList(
                ChallengeAction.of(player2, ALLOW),
                ChallengeAction.of(player2, CHALLENGE),
                ChallengeAction.of(player3, ALLOW),
                ChallengeAction.of(player3, CHALLENGE)
        ), turn.getAllowableActions());
    }
}
