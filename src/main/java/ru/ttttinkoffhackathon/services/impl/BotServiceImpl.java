package ru.ttttinkoffhackathon.services.impl;

import org.springframework.stereotype.Service;
import ru.ttttinkoffhackathon.configuration.BotConfig;
import ru.ttttinkoffhackathon.models.Figure;
import ru.ttttinkoffhackathon.services.BotService;
import ru.ttttinkoffhackathon.services.RegistrationService;
import ru.ttttinkoffhackathon.util.MatrixStringUtil;
import ru.ttttinkoffhackathon.util.MinimaxUtil;
import ru.ttttinkoffhackathon.util.PossibleMovesGeneratorUtil;

import java.util.List;

import static ru.ttttinkoffhackathon.util.Constants.FIELD_SIZE;

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

    //todo если не хватает ресурсов
    // регулировать depth относительно выставленных подряд фигур
    // если можно проиграть/выиграть через 1 ход зачем просчитывать что будет через 5
    // заготовки??
    // записывать уже просчитанные ходы и если не укладываемся в секунду возвращать лучший что успели просчитать
    // как регулировать depth??
    @Override
    public String makeTurnByGameField(String gameField) {
        List<String> possibleMoves = PossibleMovesGeneratorUtil.generatePossibleMoves(gameField, figure);
        if (possibleMoves.isEmpty() && figure == Figure.CROSS) return makeDefaultFirstTurn(gameField);
        else return makeMinimaxTurn(possibleMoves,3);
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

        if (bestMove == null) {
            throw new IllegalStateException("Нет доступных ходов");
        }

        return bestMove;
    }
}
