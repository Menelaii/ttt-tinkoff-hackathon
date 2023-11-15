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
}
