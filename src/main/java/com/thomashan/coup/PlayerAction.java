package com.thomashan.coup;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.thomashan.coup.MainAction.ASSASSINATE;
import static com.thomashan.coup.MainAction.COUP;
import static com.thomashan.coup.MainAction.STEAL;
import static java.util.Optional.empty;

public class PlayerAction {
    private static final List<MainAction> actionRequiresTarget = Arrays.asList(ASSASSINATE, STEAL, COUP);
    private final MainAction mainAction;
    private final Player player;
    private final TurnAction turnAction;
    private final Optional<Player> target;
    private final Optional<List<ChallengeAction>> challengeActions;
    private final Optional<BlockAction> blockAction;
    private final Optional<List<ChallengeAction>> blockChallengeActions;

    private PlayerAction(Player player, MainAction mainAction, Player target) {
        checkPreconditions(player, mainAction, target);

        this.player = player;
        this.mainAction = mainAction;
        this.turnAction = mainAction.getNextTurnAction();
        this.challengeActions = empty();
        this.blockAction = empty();
        this.blockChallengeActions = empty();

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    private void checkPreconditions(Player player, MainAction mainAction, Player target) {
        if (target != null) {
            checkPreconditionsTargetRequired(player, mainAction, target);
        } else {
            checkPreconditionsNoTarget(player, mainAction);
        }
    }

    private void checkPreconditionsNoTarget(Player player, MainAction mainAction) {
        if (actionRequiresTarget.contains(mainAction)) {
            throw new IllegalArgumentException("Action cannot specify target");
        }

        actionAllowed(mainAction, player);
    }

    private void checkPreconditionsTargetRequired(Player player, MainAction mainAction, Player target) {
        if (!actionRequiresTarget.contains(mainAction)) {
            throw new IllegalArgumentException("Action requires a target");
        }

        actionAllowed(mainAction, player);
        actionAllowedByTarget(mainAction, target);
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

    private void actionAllowed(MainAction mainAction, Player player) {
        if (!mainAction.isAllowable(player.getCoins())) {
            throw new IllegalArgumentException("The action is not allowed");
        }
    }

    private void actionAllowedByTarget(MainAction mainAction, Player target) {
        if (mainAction == STEAL && target.getCoins() == 0) {
            throw new IllegalArgumentException("The action is not allowed");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public MainAction getMainAction() {
        return mainAction;
    }

    public Optional<Player> getTarget() {
        return target;
    }

    public static PlayerAction of(Player player, MainAction mainAction) {
        return new PlayerAction(player, mainAction, null);
    }

    public static PlayerAction of(Player player, MainAction mainAction, Player target) {
        return new PlayerAction(player, mainAction, target);
    }
}
