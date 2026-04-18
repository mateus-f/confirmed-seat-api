package me.mateusferreira.confirmed_seat_api.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.mateusferreira.confirmed_seat_api.model.enums.UserRole;

@Schema(description = "Resumo simplificado das informações do usuário para listagens e payloads de autenticação")
public record UserSummaryResponse(

        @Schema(description = "Identificador único do usuário no banco de dados", example = "1")
        Long id,

        @Schema(description = "Nome de exibição do usuário", example = "Mateus Júnior")
        String name,

        @Schema(description = "Endereço de e-mail principal", example = "mateus@exemplo.com")
        String email,

        @Schema(description = "Perfil de acesso atribuído", example = "ADMIN")
        UserRole role
) {
}
