package ru.ttttinkoffhackathon.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ttttinkoffhackathon.configuration.BotConfig;
import ru.ttttinkoffhackathon.models.Figure;
import ru.ttttinkoffhackathon.services.BotService;
import ru.ttttinkoffhackathon.services.RegistrationService;
import ru.ttttinkoffhackathon.util.*;

import java.util.List;

import static ru.ttttinkoffhackathon.util.Constants.FIELD_SIZE;

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

    //todo если не хватает ресурсов ...
    // регулировать depth относительно выставленных подряд фигур
    //todo подстраховка по времени
    // сколько мс можно выделить на алгоритм??
    @Override
    public String makeTurnByGameField(String gameField) {
        FilledCellsTrackerUtil.updateField(gameField);

        if (FilledCellsTrackerUtil.getFilledCellsCount() == 0) {
            return makeDefaultFirstTurn(gameField);
        }

        List<String> possibleMoves = PossibleMovesGeneratorUtil.generatePossibleMoves(gameField, figure);
        return makeMinimaxTurn(possibleMoves, MinimaxDepthUtil.determineDepth());
    }

    public String makeDefaultFirstTurn(String gameField) {
        int centerIndex = MatrixStringUtil.coordinatesToIndex(FIELD_SIZE / 2, FIELD_SIZE / 2);
        StringBuilder newGameField = new StringBuilder(gameField);
        newGameField.setCharAt(centerIndex, figure.getName().charAt(0));
        return newGameField.toString();
    }

    private String makeMinimaxTurn(List<String> possibleMoves, int depth) {
        int bestScore = Integer.MIN_VALUE;
        String bestMove = null;

        for (String move : possibleMoves) {
            int score = MinimaxUtil.minimax(move, figure, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }
}
