package com.thomashan.coup;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.thomashan.coup.ChallengeAction.CHALLENGE;
import static com.thomashan.coup.TurnAction.ACTION;
import static com.thomashan.coup.TurnAction.CHALLENGE_ACTION;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class StandardTurn implements Turn {
    private final Players players;
    private final int turnNumber;
    private final Optional<MainAction> action;
    private final TurnAction turnAction;

    private StandardTurn(Players players) {
        this(players, 0);
    }

    private StandardTurn(Players players, int turnNumber) {
        this(players, turnNumber, ACTION);
    }

    private StandardTurn(Players players, int turnNumber, TurnAction turnAction) {
        this.players = players;
        this.turnNumber = turnNumber;
        this.action = empty();
        this.turnAction = turnAction;
    }

    private StandardTurn(Players players, int turnNumber, MainAction mainAction, TurnAction turnAction) {
        this.players = players;
        this.turnNumber = turnNumber;
        this.action = of(mainAction);
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
    public List<MainAction> getAllowableActions() {
        return getCurrentTurnPlayer().getAllowableMainActions();
    }

    @Override
    public Turn attemptAction(MainAction mainAction) {
        if (turnAction != ACTION) {
            throw new IllegalArgumentException("We expect you to perform " + turnAction.getDescription());
        }

        if (mainAction.isChallengeable()) {
            return new StandardTurn(players, turnNumber, mainAction, CHALLENGE_ACTION);
        }

        return new StandardTurn(players, turnNumber + 1, ACTION);
    }

    @Override
    public Turn attemptBlockAction(BlockAction blockAction) {
        return new StandardTurn(players, turnNumber, CHALLENGE_ACTION);
    }

    @Override
    public Turn challengeAction(List<PlayerChallengeAction> playerChallengeActions) {
        if (turnAction != CHALLENGE_ACTION) {
            throw new IllegalArgumentException("We expect you to perform " + turnAction.getDescription());
        }

        Supplier<Stream<PlayerChallengeAction>> challengeActionsSupplier = () -> playerChallengeActions.stream().filter(a -> a.getChallengeAction() == CHALLENGE);
//        Stream<PlayerChallengeAction> challengeActions = challengeActionsSupplier.get().count();

        if (challengeActionsSupplier.get().count() == 0) {
            return new StandardTurn(players, turnNumber + 1, ACTION);
        }

//        challengeActionsSupplier.get().findAny().get()

        return null;

    }

    public static Turn create(Players players) {
        return new StandardTurn(players);
    }
}
