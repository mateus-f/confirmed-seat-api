package me.mateusferreira.confirmed_seat_api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import me.mateusferreira.confirmed_seat_api.model.enums.BookingStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Schema(description = "Detalhamento completo de uma reserva")
@JsonInclude(NON_NULL)
public record BookingDetailResponse(
        @Schema(description = "Status atual da reserva", example = "CONFIRMED")
        BookingStatus status,

        @Schema(description = "Código único de confirmação da reserva", example = "a8098c1a-f86e-11da-bd1a-00112444201e")
        UUID confirmationCode,

        @Schema(description = "Data e hora de criação da reserva", example = "2026-06-11T11:26:00Z")
        OffsetDateTime createdAt,

        @Schema(description = "Data e hora de cancelamento (nulo se a reserva estiver ativa)", example = "2026-06-11T12:00:00Z")
        OffsetDateTime cancelledAt,

        @Schema(description = "Informações resumidas do evento reservado")
        EventSummaryResponse event,

        @Schema(description = "Informações resumidas do usuário detentor da reserva")
        UserSummaryResponse user
) {
}
