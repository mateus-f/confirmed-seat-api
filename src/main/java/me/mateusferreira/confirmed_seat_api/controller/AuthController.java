package me.mateusferreira.confirmed_seat_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.model.dto.request.UserCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.request.UserLoginRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.UserCreateResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.UserLoginResponse;
import me.mateusferreira.confirmed_seat_api.service.UserService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para registro e gestão de acesso de usuários")
public class AuthController {

    private final UserService userService;

    @Operation(
            summary = "Registrar um novo usuário",
            description = "Cria uma nova conta de usuário no sistema com base no perfil selecionado (ADMIN ou CUSTOMER)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de conta realizado com sucesso")
    })
    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public UserCreateResponse register(@Valid @RequestBody UserCreateRequest request) {
        return userService.register(request);
    }

    @Operation(
            summary = "Autenticar usuário",
            description = "Valida as credenciais e retorna um token JWT caso o e-mail e senha estejam corretos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso")
    })
    @PostMapping("/login")
    @ResponseStatus(OK)
    public UserLoginResponse login(@Valid @RequestBody UserLoginRequest request){
        return userService.login(request);
    }
}
