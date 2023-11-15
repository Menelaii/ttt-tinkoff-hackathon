package ru.ttttinkoffhackathon.util;

import ru.ttttinkoffhackathon.models.Figure;

import static ru.ttttinkoffhackathon.util.Constants.FIELD_SIZE;
import static ru.ttttinkoffhackathon.util.Constants.FIGURES_FOR_WIN;

//todo оптимизация: попробовать проверять только область изменения
// поставили х в точку (row, col) значит проверяем только для этой точки
public class WinCheckUtil {

    public static boolean checkForWin(String gameField, Figure figure) {
        return checkForWinHorizontally(gameField, figure) ||
                checkForWinVertically(gameField, figure) ||
                checkForWinDiagonally(gameField, figure.getName().charAt(0));
    }

    private static boolean checkForWinHorizontally(String gameField, Figure figure) {
        int count = 0;
        int idx;
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                idx = MatrixStringUtil.coordinatesToIndex(row, col);
                if (gameField.charAt(idx) == figure.getName().charAt(0)) {
                    count++;
                    if (count == 5) return true;
                } else {
                    count = 0;
                    if (isOutOfSpace(col + 1)) {
                        break;
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkForWinVertically(String gameField, Figure figure) {
        int count = 0;
        int idx;
        for (int col = 0; col < FIELD_SIZE; col++) {
            for (int row = 0; row < FIELD_SIZE; row++) {
                idx = MatrixStringUtil.coordinatesToIndex(row, col);
                if (gameField.charAt(idx) == figure.getName().charAt(0)) {
                    count++;
                    if (count == 5) return true;
                } else {
                    count = 0;
                    if (isOutOfSpace(row + 1)) {
                        break;
                    }
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

    private static boolean isOutOfSpace(int idx) {
        return FIELD_SIZE - idx < FIGURES_FOR_WIN;
    }
}
