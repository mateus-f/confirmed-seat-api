package me.mateusferreira.confirmed_seat_api.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import me.mateusferreira.confirmed_seat_api.model.enums.EventStatus;

import java.time.OffsetDateTime;

public record EventUpdateRequest(
        @Schema(description = "Título do evento", example = "Workshop Confirmed Seat v2")
        String title,

        @Schema(description = "Descrição detalhada", example = "Nova descrição atualizada")
        String description,

        @Schema(description = "Local ou link do evento", example = "Sala 402 - Bloco A")
        String location,

        @Schema(description = "Data e hora do evento (ISO 8601)", example = "2126-04-19T20:00:00Z")
        @Future(message = "A data do evento deve ser no futuro")
        OffsetDateTime eventDate,

        @Schema(description = "Status do Evento", example = "OPEN_FOR_BOOKING")
        EventStatus status
) {
}
