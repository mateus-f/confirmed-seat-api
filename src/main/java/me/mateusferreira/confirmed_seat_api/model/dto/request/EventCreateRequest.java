package me.mateusferreira.confirmed_seat_api.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Schema(description = "Dados para criação de um novo evento")
public record EventCreateRequest(
        @Schema(description = "Título do evento", example = "Workshop Confirmed Seat", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "O título é obrigatório")
        String title,

        @Schema(description = "Descrição detalhada", example = "Evento focado em backend com Spring Boot 3", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "A descrição é obrigatória")
        String description,

        @Schema(description = "Local ou link do evento", example = "Auditório Central ou Zoom")
        @NotBlank(message = "O local é obrigatório")
        String location,

        @Schema(description = "Data e hora do evento (ISO 8601)", example = "2126-04-18T20:00:00Z", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "A data do evento é obrigatória")
        @Future(message = "A data do evento deve ser no futuro")
        OffsetDateTime eventDate,

        @Schema(description = "Número total de vagas", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "A capacidade é obrigatória")
        @Min(value = 1, message = "A capacidade mínima deve ser de 1 vaga")
        Integer capacity
) {
}
