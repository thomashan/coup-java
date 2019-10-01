package com.thomashan.coup;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.thomashan.coup.ChallengeAction.CHALLENGE;
import static com.thomashan.coup.TurnAction.BLOCK_ACTION;
import static com.thomashan.coup.TurnAction.CHALLENGE_ACTION;
import static com.thomashan.coup.TurnAction.CHALLENGE_BLOCK;
import static com.thomashan.coup.TurnAction.MAIN_ACTION;
import static com.thomashan.coup.TurnAction.REVEAL_CHALLENGE;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class StandardTurn implements Turn {
    private final Players players;
    private final int turnNumber;
    private final Optional<MainActionType> action;
    private final TurnAction turnAction;

    private StandardTurn(Players players) {
        this(players, 0);
    }

    private StandardTurn(Players players, int turnNumber) {
        this(players, turnNumber, MAIN_ACTION);
    }

    private StandardTurn(Players players, int turnNumber, TurnAction turnAction) {
        this.players = players;
        this.turnNumber = turnNumber;
        this.action = empty();
        this.turnAction = turnAction;
    }

    private StandardTurn(Players players, int turnNumber, MainActionType mainActionType, TurnAction turnAction) {
        this.players = players;
        this.turnNumber = turnNumber;
        this.action = of(mainActionType);
        this.turnAction = turnAction;
    }

    @Override
    public int getTurnNumber() {
        return turnNumber;
    }

    @Override
    public Player getCurrentTurnPlayer() {
        return players.getPlayers().get(turnNumber % players.getNumberOfPlayers());
    }

    @Override
    public List<MainActionType> getAllowableActions() {
        return getCurrentTurnPlayer().getAllowableMainActions();
    }


    @Override
    public Turn attemptMainAction(MainActionType mainActionType) {
        if (turnAction != MAIN_ACTION) {
            throw new IllegalArgumentException("We expect you to perform " + turnAction.getDescription());
        }

        if (!getCurrentTurnPlayer().getAllowableMainActions().contains(mainActionType)) {
            throw new IllegalArgumentException("You can't perform " + mainActionType + ".You can perform the following actions " + getCurrentTurnPlayer().getAllowableMainActions());
        }

        if (mainActionType.isChallengeable()) {
            return new StandardTurn(players, turnNumber, mainActionType, CHALLENGE_ACTION);
        }

        return new StandardTurn(players, turnNumber + 1, MAIN_ACTION);
    }

    @Override
    public Turn attemptBlock(BlockAction blockAction) {
        return new StandardTurn(players, turnNumber, CHALLENGE_BLOCK);
    }

    @Override
    public Turn challengeMainAction(List<PlayerChallengeAction> playerChallengeActions) {
        if (turnAction != CHALLENGE_ACTION) {
            throw new IllegalArgumentException("We expect you to perform " + turnAction.getDescription());
        }

        Supplier<Stream<PlayerChallengeAction>> challengeActionsSupplier = () -> playerChallengeActions
                .stream()
                .filter(a -> a.getChallengeAction() == CHALLENGE);

        if (challengeActionsSupplier.get().count() == 0) {
            if (action.get().isBlockable()) {
                return new StandardTurn(players, turnNumber, action.get(), BLOCK_ACTION);
            }

            return new StandardTurn(players, turnNumber + 1, MAIN_ACTION);
        }

        return new StandardTurn(players, turnNumber, REVEAL_CHALLENGE);
    }

    @Override
    public Turn reveal() {
        return new StandardTurn(players, turnNumber + 1, MAIN_ACTION);
    }

    @Override
    public Turn challengeBlock(List<PlayerChallengeAction> playerChallengeActions) {
        if (turnAction != CHALLENGE_BLOCK) {
            throw new IllegalArgumentException("We expect you to perform " + turnAction.getDescription());
        }

        Supplier<Stream<PlayerChallengeAction>> challengeActionsSupplier = () -> playerChallengeActions
                .stream()
                .filter(a -> a.getChallengeAction() == CHALLENGE);

        if (challengeActionsSupplier.get().count() == 0) {
            return new StandardTurn(players, turnNumber + 1, MAIN_ACTION);
        }

        return new StandardTurn(players, turnNumber, REVEAL_CHALLENGE);
    }

    public static Turn create(Players players) {
        return new StandardTurn(players);
    }
}
