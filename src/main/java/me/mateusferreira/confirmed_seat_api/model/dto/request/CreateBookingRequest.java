package me.mateusferreira.confirmed_seat_api.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados de entrada para solicitar a reserva de um evento")
public record CreateBookingRequest(
        @Schema(description = "ID do evento que se deseja reservar", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "O ID do evento é obrigatório")
        Long eventId
) {
}
