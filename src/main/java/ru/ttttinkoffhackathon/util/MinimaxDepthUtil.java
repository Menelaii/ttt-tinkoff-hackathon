package ru.ttttinkoffhackathon.util;

public class MinimaxDepthUtil {
    public static int determineDepth() {
        int filledCells = FilledCellsTrackerUtil.getFilledCellsCount();

        if (filledCells < 50) return 4; // Начало игры
        else if (filledCells < 100) return 3; // Середина игры
        else if (filledCells < 150) return 2; // Позже в игре
        else return 1; // Конечная стадия игры
    }
}
