package ru.ttttinkoffhackathon.util;

import ru.ttttinkoffhackathon.models.Figure;

import java.util.HashMap;
import java.util.Map;

import static ru.ttttinkoffhackathon.util.Constants.FIELD_SIZE;
import static ru.ttttinkoffhackathon.util.Constants.FIGURES_FOR_WIN;

public class FiguresInRowCounter {

    private static final Map<String, Integer> cacheX = new HashMap<>();
    private static final Map<String, Integer> cacheO = new HashMap<>();

    public static int maxInRow(String gameField, Figure figure) {
        Map<String, Integer> cache = (figure == Figure.CROSS) ? cacheX : cacheO;

        if (cache.containsKey(gameField)) {
            return cache.get(gameField);
        }

        char figureSymbol = figure.getName().charAt(0);
        int max = 0;
        max = Math.max(maxInRowHorizontally(gameField, figureSymbol), max);
        max = Math.max(maxInRowVertically(gameField, figureSymbol), max);
        max = Math.max(maxInRowDiagonally(gameField, figureSymbol), max);

        cache.put(gameField, max);

        return max;
    }


    private static int maxInRowHorizontally(String gameField, char figure) {
        int count;
        int maxInRow = 0;
        for (int row = 0; row < FIELD_SIZE; row++) {
            count = 0;
            for (int col = 0; col < FIELD_SIZE; col++) {
                if (gameField.charAt(MatrixStringUtil.coordinatesToIndex(row, col)) == figure) {
                    count++;
                    if (count == FIGURES_FOR_WIN) return FIGURES_FOR_WIN;
                } else {
                    maxInRow = Math.max(maxInRow, count);
                    count = 0;

                    if (isOutOfSpace(col + 1)) {
                        break;
                    }
                }
            }
        }

        return maxInRow;
    }

    private static int maxInRowVertically(String gameField, char figure) {
        int maxInRow = 0;
        for (int col = 0; col < FIELD_SIZE; col++) {
            int count = 0;
            for (int row = 0; row < FIELD_SIZE; row++) {
                if (gameField.charAt(MatrixStringUtil.coordinatesToIndex(row, col)) == figure) {
                    if (++count == FIGURES_FOR_WIN) {
                        return FIGURES_FOR_WIN;
                    }
                } else {
                    maxInRow = Math.max(maxInRow, count);
                    count = 0;

                    if (isOutOfSpace(row + 1)) {
                        break;
                    }
                }
            }
        }
        return maxInRow;
    }

    private static int maxInRowDiagonally(String gameField, char figureSymbol) {
        int maxInRow = 0;
        int count;
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                count = 0;
                for (int i = 0; i < FIGURES_FOR_WIN; i++) {
                    if (gameField.charAt(MatrixStringUtil.coordinatesToIndex(row + i, col + i)) == figureSymbol) {
                        if (++count == FIGURES_FOR_WIN) {
                            return FIGURES_FOR_WIN;
                        }
                    } else {
                        maxInRow = Math.max(maxInRow, count);
                        break;
                    }
                }
            }
        }

        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = FIGURES_FOR_WIN - 1; col < FIELD_SIZE; col++) {
                count = 0;
                for (int i = 0; i < FIGURES_FOR_WIN; i++) {
                    if (gameField.charAt(MatrixStringUtil.coordinatesToIndex(row + i, col - i)) == figureSymbol) {
                        if (++count == FIGURES_FOR_WIN) {
                            return FIGURES_FOR_WIN;
                        }
                    } else {
                        maxInRow = Math.max(maxInRow, count);
                        break;
                    }
                }
            }
        }

        return maxInRow;
    }

    private static boolean isOutOfSpace(int idx) {
        return FIELD_SIZE - idx < FIGURES_FOR_WIN;
    }
}
