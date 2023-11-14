package ru.ttttinkoffhackathon.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ttttinkoffhackathon.models.gamefield.GameFieldRequestDTO;
import ru.ttttinkoffhackathon.models.gamefield.GameFieldResponseDTO;
import ru.ttttinkoffhackathon.services.BotService;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final BotService botService;

    @PostMapping("/bot/turn")
    public GameFieldResponseDTO makeTurn(@RequestBody GameFieldRequestDTO gameFieldRequestDto) {
        String gameField = gameFieldRequestDto.gameField();
        String newGameField = botService.makeTurnByGameField(gameField);
        return GameFieldResponseDTO.builder().gameField(newGameField).build();
    }
}
