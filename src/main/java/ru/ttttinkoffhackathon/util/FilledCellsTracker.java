package ru.ttttinkoffhackathon.util;

import java.util.HashSet;
import java.util.Set;

import static ru.ttttinkoffhackathon.util.Constants.EMPTY_CELL;

public class FilledCellsTracker {
    private static final Set<Integer> filledCells = new HashSet<>();

    private static int filledCellsCount = 0;

    public static void update(String gameField) {
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
