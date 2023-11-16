package ru.ttttinkoffhackathon.util;

//todo
public class MinimaxDepthUtil {
    public static int determineDepth() {
        int filledCells = FilledCellsTrackerUtil.getFilledCellsCount();

        if (filledCells < 5) return 4; // Начало
        else if (filledCells < 10) return 3; // Середина
        else if (filledCells < 15) return 2; // Позже
        else return 1; // Позже
    }
}
