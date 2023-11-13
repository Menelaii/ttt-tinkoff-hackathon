package ru.ttttinkoffhackathon.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.UUID;

@ConfigurationProperties(prefix = "config")
public record BotConfig(
        String mediatorAddress,
        String botUrl,
        String botId,
        String botPassword,
        UUID sessionUUID,
        int gameFieldSize
) {}