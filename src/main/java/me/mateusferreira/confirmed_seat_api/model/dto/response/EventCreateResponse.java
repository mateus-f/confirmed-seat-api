package me.mateusferreira.confirmed_seat_api.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.mateusferreira.confirmed_seat_api.model.enums.EventStatus;

import java.time.OffsetDateTime;

@Schema(description = "Resposta após a criação bem-sucedida de um evento")
public record EventCreateResponse(
        @Schema(description = "ID único gerado pelo banco de dados", example = "1")
        Long id,

        @Schema(description = "Título confirmado", example = "Workshop Confirmed Seat")
        String title,

        @Schema(description = "Data agendada", example = "2126-04-18T20:00:00Z")
        OffsetDateTime eventDate,

        @Schema(description = "Capacidade total definida", example = "100")
        Integer capacity,

        @Schema(description = "Status atual do evento", example = "OPEN_FOR_BOOKING")
        EventStatus status
) {
}
