package ru.ttttinkoffhackathon.util;

import ru.ttttinkoffhackathon.models.Figure;

import java.util.HashMap;
import java.util.Map;

import static ru.ttttinkoffhackathon.util.Constants.FIELD_SIZE;
import static ru.ttttinkoffhackathon.util.Constants.FIGURES_FOR_WIN;

public class WinChecker {

    private static final Map<String, Boolean> cacheX = new HashMap<>();
    private static final Map<String, Boolean> cacheO = new HashMap<>();

    public static boolean checkForWin(String gameField, Figure figure) {
        Map<String, Boolean> cache = (figure == Figure.CROSS) ? cacheX : cacheO;

        if (cache.containsKey(gameField)) {
            return cache.get(gameField);
        }

        char figureSymbol = figure.getName().charAt(0);
        boolean result = checkForWinHorizontally(gameField, figureSymbol) ||
                checkForWinVertically(gameField, figureSymbol) ||
                checkForWinDiagonally(gameField, figureSymbol);

        cache.put(gameField, result);

        return result;
    }

    private static boolean checkForWinHorizontally(String gameField, char figure) {
        int count;
        for (int row = 0; row < FIELD_SIZE; row++) {
            count = 0;
            for (int col = 0; col <= FIELD_SIZE - FIGURES_FOR_WIN; col++) {
                if (gameField.charAt(MatrixStringUtil.coordinatesToIndex(row, col)) == figure) {
                    count++;
                    if (count == FIGURES_FOR_WIN) return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private static boolean checkForWinVertically(String gameField, char figure) {
        int count;
        for (int col = 0; col < FIELD_SIZE; col++) {
            count = 0;
            for (int row = 0; row <= FIELD_SIZE - FIGURES_FOR_WIN; row++) {
                if (gameField.charAt(MatrixStringUtil.coordinatesToIndex(row, col)) == figure) {
                    count++;
                    if (count == FIGURES_FOR_WIN) return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private static boolean checkForWinDiagonally(String gameField, char figureSymbol) {
        for (int row = 0; row <= FIELD_SIZE - FIGURES_FOR_WIN; row++) {
            for (int col = 0; col <= FIELD_SIZE - FIGURES_FOR_WIN; col++) {
                int count = 0;
                for (int i = 0; i < FIGURES_FOR_WIN; i++) {
                    if (gameField.charAt(MatrixStringUtil.coordinatesToIndex(row + i, col + i)) == figureSymbol) {
                        if (++count == FIGURES_FOR_WIN) {
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        for (int row = 0; row <= FIELD_SIZE - FIGURES_FOR_WIN; row++) {
            for (int col = FIGURES_FOR_WIN - 1; col < FIELD_SIZE; col++) {
                int count = 0;
                for (int i = 0; i < FIGURES_FOR_WIN; i++) {
                    if (gameField.charAt(MatrixStringUtil.coordinatesToIndex(row + i, col - i)) == figureSymbol) {
                        if (++count == FIGURES_FOR_WIN) {
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        return false;
    }
}
