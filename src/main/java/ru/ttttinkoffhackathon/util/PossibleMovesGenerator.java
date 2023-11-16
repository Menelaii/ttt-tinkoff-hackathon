package ru.ttttinkoffhackathon.util;

import ru.ttttinkoffhackathon.models.Figure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.ttttinkoffhackathon.util.Constants.EMPTY_CELL;
import static ru.ttttinkoffhackathon.util.Constants.FIELD_SIZE;

public class PossibleMovesGenerator {

    public static List<String> generatePossibleMoves(String gameField, Figure figure) {
        List<String> possibleMoves = new ArrayList<>();
        Set<Integer> checkedIndices = new HashSet<>();

        for (Integer filledIndex : FilledCellsTracker.getFilledCells()) {
            List<Integer> neighbours = getNeighbourIndices(filledIndex);

            for (Integer neighbour : neighbours) {
                if (!checkedIndices.contains(neighbour) && gameField.charAt(neighbour) == EMPTY_CELL) {
                    StringBuilder newGameField = new StringBuilder(gameField);
                    newGameField.setCharAt(neighbour, figure.getName().charAt(0));
                    possibleMoves.add(newGameField.toString());
                    checkedIndices.add(neighbour);
                }
            }
        }

        return possibleMoves;
    }

    private static List<Integer> getNeighbourIndices(int index) {
        int row = MatrixStringUtil.indexToRow(index);
        int col = MatrixStringUtil.indexToCol(index);

        List<Integer> neighbours = new ArrayList<>();

        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1},
                {-1, -1},
                {-1, 1},
                {1, -1},
                {1, 1}
        };

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (newRow >= 0 && newRow < FIELD_SIZE && newCol >= 0 && newCol < FIELD_SIZE) {
                neighbours.add(MatrixStringUtil.coordinatesToIndex(newRow, newCol));
            }
        }

        return neighbours;
    }
}
