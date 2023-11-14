package ru.ttttinkoffhackathon.models.registration;

import lombok.Builder;
import ru.ttttinkoffhackathon.models.Figure;

@Builder
public record RegistrationResponseDTO(
        Figure figure
) {}