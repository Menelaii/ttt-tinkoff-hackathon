package ru.ttttinkoffhackathon.util;

public class MinimaxDepthUtil {
    public static int determineDepth() {
        int filledCells = FilledCellsTrackerUtil.getFilledCellsCount();

        if (filledCells < 5) return 4; // Начало игры
        else if (filledCells < 10) return 3; // Середина игры
        else if (filledCells < 15) return 2; // Позже в игре
        else return 1; // Конечная стадия игры
    }
}
