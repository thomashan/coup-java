package com.thomashan.coup;

import java.util.List;

import static com.thomashan.coup.Action.ASSASSINATE;
import static com.thomashan.coup.Action.EXCHANGE;
import static com.thomashan.coup.Action.STEAL;
import static com.thomashan.coup.Action.TAX;
import static com.thomashan.coup.BlockAction.BLOCK_ASSASSINATE;
import static com.thomashan.coup.BlockAction.BLOCK_FOREIGN_AID;
import static com.thomashan.coup.BlockAction.BLOCK_STEAL;
import static com.thomashan.coup.Card.AMBASSADOR;
import static com.thomashan.coup.Card.ASSASSIN;
import static com.thomashan.coup.Card.CAPTAIN;
import static com.thomashan.coup.Card.CONTESSA;
import static com.thomashan.coup.Card.DUKE;

public class ActionDetector {
    private ActionDetector() {
        throw new AssertionError();
    }

    public static boolean isBluff(Action action, List<Card> cards) {
        if (action == ASSASSINATE && !cards.contains(ASSASSIN)) {
            return true;
        }

        if (action == EXCHANGE && !cards.contains(AMBASSADOR)) {
            return true;
        }

        if (action == STEAL && !cards.contains(CAPTAIN)) {
            return true;
        }

        if (action == TAX && !cards.contains(DUKE)) {
            return true;
        }

        return false;
    }

    public static boolean isBluff(BlockAction blockAction, List<Card> cards) {
        if (blockAction == BLOCK_ASSASSINATE && !cards.contains(CONTESSA)) {
            return true;
        }

        if (blockAction == BLOCK_STEAL) {
            if (!cards.contains(AMBASSADOR) && !cards.contains(CAPTAIN)) {
                return true;
            }
        }

        if (blockAction == BLOCK_FOREIGN_AID && !cards.contains(DUKE)) {
            return true;
        }

        return false;
    }
}
