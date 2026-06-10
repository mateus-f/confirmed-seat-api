package me.mateusferreira.confirmed_seat_api.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.mateusferreira.confirmed_seat_api.model.enums.BookingStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateBookingResponse(
        @Schema(description = "Código público, único e seguro da reserva", example = "a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d")
        UUID confirmationCode,

        @Schema(description = "ID do evento para referência rápida no front-end", example = "12")
        Long eventId,

        @Schema(description = "Título do evento")
        String eventTitle,

        @Schema(description = "Data e hora em que o evento vai acontecer")
        OffsetDateTime eventDate,

        @Schema(description = "Nome do participante inscrito")
        String userName,

        @Schema(description = "Status atual da inscrição (CONFIRMED ou WAITING)")
        BookingStatus status,

        @Schema(description = "Data e hora em que a inscrição foi realizada")
        OffsetDateTime createdAt
) {
}
