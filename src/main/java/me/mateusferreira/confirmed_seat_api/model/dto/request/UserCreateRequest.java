package me.mateusferreira.confirmed_seat_api.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import me.mateusferreira.confirmed_seat_api.model.enums.UserRole;
import org.hibernate.validator.constraints.br.CPF;

public record UserCreateRequest(
        @Schema(description = "Nome completo do usuário", example = "Mateus Júnior")
        @NotBlank(message = "Nome é obrigatório.")
        @Size(min = 3, max = 150, message = "Nome deve conter no mínimo 3 caracteres e no máximo 150 caracteres.")
        String name,

        @Schema(description = "Endereço de e-mail único", example = "mateus@exemplo.com")
        @NotBlank(message = "E-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        String email,

        @Schema(description = "Cadastro de Pessoa Física (CPF)", example = "381.806.430-00")
        @NotBlank(message = "CPF é obrigatório.")
        @CPF(message = "CPF inválido")
        String cpf,

        @Schema(description = "Senha forte do usuário", example = "Abc@1234", format = "password")
        @NotBlank(message = "Senha é obrigatória.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).{8,}$", message = "Senha deve ter no mínimo 8 caracteres e incluir letras maiúsculas, minúsculas, números e pelo menos um símbolo (ex: @, #, $, *).")
        String password,

        @Schema(description = "Perfil de acesso no sistema", example = "CUSTOMER")
        @NotNull(message = "O perfil (ADMIN ou COSTUMER) é obrigatório.")
        UserRole role
) {
    public UserCreateRequest {
        cpf = cpf != null ? cpf.replaceAll("\\D", "") : null;
    }
}
