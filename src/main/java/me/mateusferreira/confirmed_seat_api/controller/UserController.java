package me.mateusferreira.confirmed_seat_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.model.dto.response.ErrorResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.UserProfileResponse;
import me.mateusferreira.confirmed_seat_api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento e consulta de dados dos usuários")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Obter dados do usuário atual",
            description = "Retorna as informações detalhadas do usuário autenticado via token JWT.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do perfil retornados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/me")
    public UserProfileResponse me() {
        return userService.getMe();
    }
}
