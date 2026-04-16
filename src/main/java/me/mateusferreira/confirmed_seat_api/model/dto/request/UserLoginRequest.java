package me.mateusferreira.confirmed_seat_api.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados de entrada para autenticação")
public record UserLoginRequest(
        @Schema(description = "E-mail cadastrado do usuário", example = "mateus@exemplo.com")
        @NotBlank(message = "E-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        String email,

        @Schema(description = "Senha do usuário", example = "Abc@1234", format = "password")
        @NotBlank(message = "Senha é obrigatória.")
        String password
) {
    public UserLoginRequest {
        email = email.toLowerCase();
    }
}
