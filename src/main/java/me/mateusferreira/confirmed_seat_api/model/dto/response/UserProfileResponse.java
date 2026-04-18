package me.mateusferreira.confirmed_seat_api.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.mateusferreira.confirmed_seat_api.model.enums.UserRole;

import java.time.OffsetDateTime;

@Schema(description = "Perfil completo do usuário logado")
public record UserProfileResponse(

        @Schema(description = "ID único", example = "1")
        Long id,

        @Schema(description = "Nome completo", example = "Mateus Júnior")
        String name,

        @Schema(description = "E-mail de contato", example = "mateus@exemplo.com")
        String email,

        @Schema(description = "Documento de identidade", example = "123.456.789-00")
        String cpf,

        @Schema(description = "Papel de acesso", example = "ADMIN")
        UserRole role,

        @Schema(description = "Data de criação da conta", example = "2026-04-18T18:25:00Z")
        OffsetDateTime createdAt) {
}
