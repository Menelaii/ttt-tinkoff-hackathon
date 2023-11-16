package ru.ttttinkoffhackathon.util;

import ru.ttttinkoffhackathon.models.Figure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimaxUtil {
    private static final Map<String, Integer> cacheO = new HashMap<>();
    private static final Map<String, Integer> cacheX = new HashMap<>();

    public static int minimax(String gameField, Figure ourFigure, int depth, int alpha, int beta, boolean isMaximizing) {
        if (depth == 0 || isGameOver(gameField)) {
            return evaluate(gameField, ourFigure, depth);
        }

        FilledCellsTrackerUtil.updateField(gameField);
        List<String> possibleMoves = PossibleMovesGeneratorUtil.generatePossibleMoves(
                gameField,
                isMaximizing ? ourFigure : Figure.getOppositeFigure(ourFigure)
        );

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (String move : possibleMoves) {
                int score = minimax(move, ourFigure, depth - 1, alpha, beta, false);
                bestScore = Math.max(bestScore, score);
                alpha = Math.max(alpha, score);
                if (beta <= alpha) {
                    break;
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (String move : possibleMoves) {
                int score = minimax(move, ourFigure, depth - 1, alpha, beta, true);
                bestScore = Math.min(bestScore, score);
                beta = Math.min(beta, score);
                if (beta <= alpha) {
                    break;
                }
            }
            return bestScore;
        }
    }

    public static int evaluate(String gameField, Figure ourFigure, int depth) {
        Map<String, Integer> cache = ourFigure == Figure.CROSS ? cacheX : cacheO;

        if (cache.containsKey(gameField)) {
            return cache.get(gameField);
        }

        Figure opponentFigure = Figure.getOppositeFigure(ourFigure);

        boolean weWon = WinCheckUtil.checkForWin(gameField, ourFigure);
        boolean theyWon = WinCheckUtil.checkForWin(gameField, opponentFigure);

        int score;

        if (weWon) {
            score = 10 - depth;
        } else if (theyWon) {
            score = -10 + depth;
        } else {
            score = 0;
        }

        cache.put(gameField, score);

        return score;
    }

    private static boolean isBoardFull(String gameField) {
        return !gameField.contains("_");
    }

    public static boolean isGameOver(String gameField) {
        if (isBoardFull(gameField)) {
            return true;
        }

        Figure[] figures = {Figure.CROSS, Figure.ZERO};
        for (Figure figure : figures) {
            if (WinCheckUtil.checkForWin(gameField, figure)) {
                return true;
            }
        }

        return false;
    }
}
