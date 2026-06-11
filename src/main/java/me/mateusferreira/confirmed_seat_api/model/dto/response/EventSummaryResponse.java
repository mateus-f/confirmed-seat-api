package me.mateusferreira.confirmed_seat_api.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.mateusferreira.confirmed_seat_api.model.enums.EventStatus;

import java.time.OffsetDateTime;

@Schema(description = "Resumo simplificado do evento para listagens")
public record EventSummaryResponse(
        @Schema(description = "Identificador único do evento", example = "1")
        Long id,

        @Schema(description = "Título do evento", example = "Workshop Confirmed Seat")
        String title,

        @Schema(description = "Local do evento", example = "Auditório Central")
        String location,

        @Schema(description = "Data e hora do evento", example = "2126-04-18T20:00:00Z")
        OffsetDateTime eventDate,

        @Schema(description = "Capacidade máxima de vagas do evento", example = "100")
        Integer capacity,

        @Schema(description = "Status atual do evento", example = "OPEN")
        EventStatus status
) {
}
