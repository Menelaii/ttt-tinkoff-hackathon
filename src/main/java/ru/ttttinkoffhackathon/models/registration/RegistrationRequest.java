package ru.ttttinkoffhackathon.models.registration;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RegistrationRequest(
        String botUrl,
        String botId,
        String password
) {}