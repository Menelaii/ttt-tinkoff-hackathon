package ru.ttttinkoffhackathon.services.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ttttinkoffhackathon.configuration.BotConfig;
import ru.ttttinkoffhackathon.exceptions.RegistrationException;
import ru.ttttinkoffhackathon.models.Figure;
import ru.ttttinkoffhackathon.models.registration.RegistrationRequestDTO;
import ru.ttttinkoffhackathon.models.registration.RegistrationResponseDTO;
import ru.ttttinkoffhackathon.services.RegistrationService;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final RestTemplate restTemplate;
    private final BotConfig botConfig;

    private Figure figure;

    @PostConstruct
    public void init() {
        String botUrl = botConfig.botUrl();
        String botId = botConfig.botId();
        UUID sessionUUID = botConfig.sessionUUID();
        log.info("Попытка зарегистрировать бота {} с url {} в сессии {}", botId, botUrl, sessionUUID);

        log.debug("Отправляем запрос для регистрации бота в сессии");
        final var uri = UriComponentsBuilder
                .fromUriString("{basePath}/sessions/{sessionId}/registration")
                .buildAndExpand(Map.of(
                        "basePath", botConfig.mediatorAddress(),
                        "sessionId", sessionUUID
                ))
                .encode()
                .toUri();
        final var body = RegistrationRequestDTO.builder()
                .botUrl(botUrl)
                .botId(botId)
                .password(botConfig.botPassword())
                .build();
        ResponseEntity<RegistrationResponseDTO> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(body),
                RegistrationResponseDTO.class
        );

        log.debug("Ответ получен {}", response);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RegistrationException();
        }

        this.figure = response.getBody().figure();
        log.debug("Бот успешно зарегистрирован в сессии {}", sessionUUID);
        log.info("Успешно зарегистрирован. Буду ходить фигурой {}", figure);
    }

    @Override
    public Figure getFigure() {
        return figure;
    }
}
