package me.mateusferreira.confirmed_seat_api.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de autenticação bem-sucedida")
public record UserLoginResponse(
        @Schema(description = "JWT Token para acesso aos endpoints protegidos", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token,

        @Schema(description = "Tempo de expiração do token em segundos", example = "3600")
        long expiresIn,

        @Schema(description = "Resumo dos dados do usuário logado")
        UserSummaryResponse user
) {
}
