package ru.ttttinkoffhackathon.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ttttinkoffhackathon.models.gamefield.GameFieldRequestDTO;
import ru.ttttinkoffhackathon.models.gamefield.GameFieldResponseDTO;
import ru.ttttinkoffhackathon.services.BotService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {
    private final BotService botService;

    @PostMapping("/bot/turn")
    public GameFieldResponseDTO makeTurn(@RequestBody GameFieldRequestDTO gameFieldRequestDto) {
        long start = System.currentTimeMillis();
        String gameField = gameFieldRequestDto.gameField();
        String newGameField = botService.makeTurnByGameField(gameField);
        long end = System.currentTimeMillis();

        log.info("затраченное время: " + (end - start));

        return GameFieldResponseDTO.builder().gameField(newGameField).build();
    }
}
