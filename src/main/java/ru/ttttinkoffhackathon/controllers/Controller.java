package ru.ttttinkoffhackathon.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.reactor.DebugAgentEnvironmentPostProcessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ttttinkoffhackathon.models.Figure;
import ru.ttttinkoffhackathon.models.gamefield.GameFieldRequestDTO;
import ru.ttttinkoffhackathon.models.gamefield.GameFieldResponseDTO;
import ru.ttttinkoffhackathon.services.BotService;
import ru.ttttinkoffhackathon.util.DangerTracker;
import ru.ttttinkoffhackathon.util.FiguresInRowCounter;
import ru.ttttinkoffhackathon.util.MatrixStringUtil;
import ru.ttttinkoffhackathon.util.WinChecker;

import static ru.ttttinkoffhackathon.util.Constants.FIGURES_FOR_WIN;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {
    private final BotService botService;

    @PostMapping("/bot/turn")
    public GameFieldResponseDTO makeTurn(@RequestBody GameFieldRequestDTO gameFieldRequestDto) {

//        long start = System.currentTimeMillis();
        String gameField = gameFieldRequestDto.gameField();
        String newGameField = botService.makeTurnByGameField(gameField);
//        long end = System.currentTimeMillis();
//        log.info("затраченное время: " + (end - start) + "ms");
//
//        log.info("исходное поле:");
//        System.out.println(MatrixStringUtil.formatField(gameField));
//        log.info("\n поле после хода:");
//        System.out.println(MatrixStringUtil.formatField(newGameField));

        return GameFieldResponseDTO.builder().gameField(newGameField).build();
    }
}
