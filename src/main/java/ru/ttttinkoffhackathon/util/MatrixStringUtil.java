package ru.ttttinkoffhackathon.util;

import static ru.ttttinkoffhackathon.util.Constants.FIELD_SIZE;

public class MatrixStringUtil {

    public static int indexToRow(int index) {
        return index / FIELD_SIZE;
    }

    public static int indexToCol(int index) {
        return index % FIELD_SIZE;
    }

    public static int coordinatesToIndex(int row, int col) {
        return row * FIELD_SIZE + col;
    }

    public static String formatField(String gameField) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                builder.append(gameField.charAt(MatrixStringUtil.coordinatesToIndex(i,j)));
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}
