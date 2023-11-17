package ru.ttttinkoffhackathon.util;

public class BestResultTracker {
    private static int bestScore = Integer.MIN_VALUE;
    private static String bestMove = null;

    public static synchronized void tryUpdateBestMove(int score, String move) {
        if (score > bestScore) {
            bestScore = score;
            bestMove = move;
        }

        //todo
        System.out.println("возможный ход с рейтингом: " + score);
        System.out.println(MatrixStringUtil.formatField(move));
    }

    public static synchronized void reset() {
        bestScore = Integer.MIN_VALUE;
        bestMove = null;
    }

    public static synchronized String getBestMove() {
        return bestMove;
    }
}