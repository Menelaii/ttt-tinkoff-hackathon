package ru.ttttinkoffhackathon.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ttttinkoffhackathon.configuration.BotConfig;
import ru.ttttinkoffhackathon.models.Figure;
import ru.ttttinkoffhackathon.services.BotService;
import ru.ttttinkoffhackathon.services.RegistrationService;
import ru.ttttinkoffhackathon.util.*;

import java.util.List;
import java.util.concurrent.*;

import static ru.ttttinkoffhackathon.util.Constants.FIELD_SIZE;
import static ru.ttttinkoffhackathon.util.Constants.SAFE_TIME_OUT;

@Slf4j
@Service
public class BotServiceImpl implements BotService {
    private final Figure figure;

    //todo bot config пробрасывать значения из него в утилитные классы
    public BotServiceImpl(
            RegistrationService registrationService,
            BotConfig botConfig
    ) {
        figure = registrationService.getFigure();
    }

    @Override
    public String makeTurnByGameField(String gameField) {
        FilledCellsTracker.update(gameField);

        if (isFirstMove()) {
            return makeDefaultFirstTurn(gameField);
        }

        return makeMinimaxTurnWithTimeout(gameField, SAFE_TIME_OUT);
    }

    private String makeDefaultFirstTurn(String gameField) {
        int centerIndex = MatrixStringUtil.coordinatesToIndex(FIELD_SIZE / 2, FIELD_SIZE / 2);
        StringBuilder newGameField = new StringBuilder(gameField);
        newGameField.setCharAt(centerIndex, figure.getName().charAt(0));
        return newGameField.toString();
    }

    private String makeMinimaxTurnWithTimeout(String gameField, long timeout) {
        BestResultTracker.reset();

        int depth = MinimaxDepthCalculator.determineDepth();
        List<String> possibleMoves = PossibleMovesGenerator.generatePossibleMoves(gameField, figure);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() ->
                makeMinimaxTurn(possibleMoves, depth)
        );

        try {
            future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.toString());
        } finally {
            executor.shutdownNow();
        }

        return BestResultTracker.getBestMove();
    }

    private void makeMinimaxTurn(List<String> possibleMoves, int depth) {
        for (String move : possibleMoves) {
            int score = MinimaxImpl.minimax(move, figure, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
            BestResultTracker.tryUpdateBestMove(score, move);
        }
    }

    private boolean isFirstMove() {
        return FilledCellsTracker.getFilledCellsCount() == 0;
    }
}
