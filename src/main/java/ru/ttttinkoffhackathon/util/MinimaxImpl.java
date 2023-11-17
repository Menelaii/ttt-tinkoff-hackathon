package ru.ttttinkoffhackathon.util;

import ru.ttttinkoffhackathon.models.Figure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.ttttinkoffhackathon.util.Constants.FIGURES_FOR_WIN;

public class MinimaxImpl {
    private static final Map<String, Integer> cacheO = new HashMap<>();
    private static final Map<String, Integer> cacheX = new HashMap<>();

    public static int minimax(String gameField, Figure ourFigure, int depth, int alpha, int beta, boolean isMaximizing) {
        if (depth == 0 || isGameOver(gameField)) {
            return evaluate(gameField, ourFigure, depth);
        }

        FilledCellsTracker.update(gameField);
        List<String> possibleMoves = PossibleMovesGenerator.generatePossibleMoves(
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


        int ourMaxInRow = FiguresInRowCounter.maxInRow(gameField, ourFigure);
        int opponentMaxInRow = FiguresInRowCounter.maxInRow(gameField, Figure.getOppositeFigure(ourFigure));

        // todo если тестить отдельно то работает, здесь же - нет
//        DangerTracker.reset();
//        boolean isMyAssInDanger = DangerTracker.getIsMyAssInDanger();

        int score = 0;

        if (ourMaxInRow == FIGURES_FOR_WIN - 1) {
            if (opponentMaxInRow == FIGURES_FOR_WIN - 1) {
                score -= 10;
            }
        }


        if (ourMaxInRow == FIGURES_FOR_WIN) {
            score += 100 + depth;

//            if (isMyAssInDanger) {
//                score -= depth + 1;
//            }
        } else if (opponentMaxInRow == FIGURES_FOR_WIN || opponentMaxInRow == FIGURES_FOR_WIN - 1) {
            score += -100 - depth;
        } else {
            if (opponentMaxInRow < ourMaxInRow) {
                score += (ourMaxInRow * 15) - (opponentMaxInRow * 10);
            } else {
                score += (ourMaxInRow * 10) - (opponentMaxInRow * 15);
            }
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
            if (FiguresInRowCounter.maxInRow(gameField, figure) == FIGURES_FOR_WIN) {
                return true;
            }
        }

        return false;
    }
}
