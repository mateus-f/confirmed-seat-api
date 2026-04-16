package me.mateusferreira.confirmed_seat_api.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.mateusferreira.confirmed_seat_api.model.enums.UserRole;

import java.time.OffsetDateTime;

public record UserCreateResponse(
        @Schema(description = "ID gerado pelo sistema", example = "1")
        Long id,

        @Schema(description = "E-mail cadastrado", example = "mateus@exemplo.com")
        String email,

        @Schema(description = "Perfil atribuído", example = "CUSTOMER")
        UserRole role,

        @Schema(description = "Data e hora do registro", example = "2026-04-14T15:00:00Z")
        OffsetDateTime createdAt) {
}
