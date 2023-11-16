package ru.ttttinkoffhackathon.util;

public class MinimaxDepthCalculator {
    private static final int EARLY_GAME = 4;
    private static final int MID_GAME = 15;
    private static final int LATE_GAME = 20;

    public static int determineDepth() {
        int filledCells = FilledCellsTracker.getFilledCellsCount();

        if (filledCells < EARLY_GAME) return 4;
        else if (filledCells < MID_GAME) return 3;
        else if (filledCells < LATE_GAME) return 2;
        else return 1;
    }
}
