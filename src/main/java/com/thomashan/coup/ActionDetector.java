package com.thomashan.coup;

import java.util.List;

import static com.thomashan.coup.BlockAction.BLOCK_ASSASSINATE;
import static com.thomashan.coup.BlockAction.BLOCK_FOREIGN_AID;
import static com.thomashan.coup.BlockAction.BLOCK_STEAL;
import static com.thomashan.coup.Card.AMBASSADOR;
import static com.thomashan.coup.Card.ASSASSIN;
import static com.thomashan.coup.Card.CAPTAIN;
import static com.thomashan.coup.Card.CONTESSA;
import static com.thomashan.coup.Card.DUKE;
import static com.thomashan.coup.MainAction.ASSASSINATE;
import static com.thomashan.coup.MainAction.EXCHANGE;
import static com.thomashan.coup.MainAction.STEAL;
import static com.thomashan.coup.MainAction.TAX;

public class ActionDetector {
    private ActionDetector() {
        throw new AssertionError();
    }

    public static boolean isBluff(MainAction mainAction, List<Card> cards) {
        if (mainAction == ASSASSINATE && !cards.contains(ASSASSIN)) {
            return true;
        }

        if (mainAction == EXCHANGE && !cards.contains(AMBASSADOR)) {
            return true;
        }

        if (mainAction == STEAL && !cards.contains(CAPTAIN)) {
            return true;
        }

        if (mainAction == TAX && !cards.contains(DUKE)) {
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
