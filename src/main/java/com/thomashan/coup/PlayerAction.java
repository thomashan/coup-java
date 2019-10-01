package com.thomashan.coup;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.thomashan.coup.MainActionType.ASSASSINATE;
import static com.thomashan.coup.MainActionType.COUP;
import static com.thomashan.coup.MainActionType.STEAL;
import static java.util.Optional.empty;

public class PlayerAction {
    private static final List<MainActionType> actionRequiresTarget = Arrays.asList(ASSASSINATE, STEAL, COUP);
    private final MainActionType mainActionType;
    private final Player player;
    private final TurnAction turnAction;
    private final Optional<Player> target;
    private final Optional<List<ChallengeAction>> challengeActions;
    private final Optional<BlockAction> blockAction;
    private final Optional<List<ChallengeAction>> blockChallengeActions;

    private PlayerAction(Player player, MainActionType mainActionType, Player target) {
        checkPreconditions(player, mainActionType, target);

        this.player = player;
        this.mainActionType = mainActionType;
        this.turnAction = mainActionType.getNextTurnAction();
        this.challengeActions = empty();
        this.blockAction = empty();
        this.blockChallengeActions = empty();

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    private void checkPreconditions(Player player, MainActionType mainActionType, Player target) {
        if (target != null) {
            checkPreconditionsTargetRequired(player, mainActionType, target);
        } else {
            checkPreconditionsNoTarget(player, mainActionType);
        }
    }

    private void checkPreconditionsNoTarget(Player player, MainActionType mainActionType) {
        if (actionRequiresTarget.contains(mainActionType)) {
            throw new IllegalArgumentException("Action cannot specify target");
        }

        actionAllowed(mainActionType, player);
    }

    private void checkPreconditionsTargetRequired(Player player, MainActionType mainActionType, Player target) {
        if (!actionRequiresTarget.contains(mainActionType)) {
            throw new IllegalArgumentException("Action requires a target");
        }

        actionAllowed(mainActionType, player);
        actionAllowedByTarget(mainActionType, target);
        playerNotSameAsTarget(player, target);
        targetActive(target);
    }

    private void targetActive(Player target) {
        if (!target.isActive()) {
            throw new IllegalArgumentException("Target player is inactive.");
        }
    }

    private void playerNotSameAsTarget(Player player, Player target) {
        if (player.equals(target)) {
            throw new IllegalArgumentException("The player performing the action is the target?!");
        }
    }

    private void actionAllowed(MainActionType mainActionType, Player player) {
        if (!mainActionType.isAllowable(player.getCoins())) {
            throw new IllegalArgumentException("The action is not allowed");
        }
    }

    private void actionAllowedByTarget(MainActionType mainActionType, Player target) {
        if (mainActionType == STEAL && target.getCoins() == 0) {
            throw new IllegalArgumentException("The action is not allowed");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public MainActionType getMainActionType() {
        return mainActionType;
    }

    public Optional<Player> getTarget() {
        return target;
    }

    public static PlayerAction of(Player player, MainActionType mainActionType) {
        return new PlayerAction(player, mainActionType, null);
    }

    public static PlayerAction of(Player player, MainActionType mainActionType, Player target) {
        return new PlayerAction(player, mainActionType, target);
    }
}
