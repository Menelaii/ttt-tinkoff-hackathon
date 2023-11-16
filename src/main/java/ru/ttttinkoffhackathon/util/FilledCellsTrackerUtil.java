package ru.ttttinkoffhackathon.util;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

import static ru.ttttinkoffhackathon.util.Constants.EMPTY_CELL;

public class FilledCellsTrackerUtil {
    private static final int BOARD_SIZE = 361;
    private static final Set<Integer> filledCells = new HashSet<>();

    private static int filledCellsCount = 0;

    public static void updateField(String gameField) {
        filledCells.clear();
        filledCellsCount = 0;
        for (int i = 0; i < gameField.length(); i++) {
            if (gameField.charAt(i) != EMPTY_CELL) {
                filledCellsCount++;
                filledCells.add(i);
            }
        }
    }

    public static Set<Integer> getFilledCells() {
        return filledCells;
    }

    public static int getFilledCellsCount() {
        return filledCellsCount;
    }
}
