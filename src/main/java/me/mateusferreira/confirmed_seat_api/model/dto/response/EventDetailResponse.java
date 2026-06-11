package me.mateusferreira.confirmed_seat_api.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.mateusferreira.confirmed_seat_api.model.enums.EventStatus;

import java.time.OffsetDateTime;

@Schema(description = "Detalhamento completo de um evento e seu status de ocupação")
public record EventDetailResponse(
        @Schema(description = "Identificador único do evento", example = "1")
        Long id,

        @Schema(description = "Título do evento", example = "Workshop Confirmed Seat")
        String title,

        @Schema(description = "Descrição detalhada do evento", example = "Evento focado em backend com Spring Boot 3")
        String description,

        @Schema(description = "Local físico ou link do evento", example = "Auditório Central")
        String location,

        @Schema(description = "Data e hora agendada para o evento", example = "2126-04-18T20:00:00Z")
        OffsetDateTime eventDate,

        @Schema(description = "Capacidade total de vagas cadastradas", example = "100")
        Integer capacity,

        @Schema(description = "Quantidade de vagas atualmente reservadas", example = "35")
        Integer reservedSeats,

        @Schema(description = "Quantidade de vagas ainda disponíveis para reserva", example = "65")
        Integer availableSeats,

        @Schema(description = "Status de comercialização do evento", example = "OPEN")
        EventStatus status
) {
}
