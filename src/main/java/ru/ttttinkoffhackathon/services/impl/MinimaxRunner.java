package ru.ttttinkoffhackathon.services.impl;

import ru.ttttinkoffhackathon.models.Figure;

import java.util.concurrent.*;

public class MinimaxRunner {
//    private static final int TIME_LIMIT = 1; // Ограничение времени в секундах
//
//    public static int runMinimaxWithTimeout(String gameField, Figure ourFigure, int depth) {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Future<Integer> future = executor.submit(() -> minimax(gameField, ourFigure, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true));
//
//        try {
//            // Пытаемся получить результат в течение TIME_LIMIT секунд
//            return future.get(TIME_LIMIT, TimeUnit.SECONDS);
//        } catch (TimeoutException e) {
//            // Превышено время ожидания - возвращаем лучший результат на данный момент
//            future.cancel(true); // Отменяем задачу
//            return bestResultSoFar(); // Это должен быть метод, возвращающий лучший результат
//        } catch (InterruptedException | ExecutionException e) {
//            // Обработка других исключений
//            e.printStackTrace();
//            return -1; // Или другое значение по умолчанию
//        } finally {
//            executor.shutdown();
//        }
//    }

//    private static int minimax(String gameField, Figure ourFigure, int depth, int alpha, int beta, boolean isMaximizing) {
//        // Реализация Minimax...
//    }

//    private static int bestResultSoFar() {
//        // Возвращаем лучший результат, который удалось получить до прерывания
//        // Это может быть последний вычисленный результат или предварительно сохраненное значение
//    }
}
