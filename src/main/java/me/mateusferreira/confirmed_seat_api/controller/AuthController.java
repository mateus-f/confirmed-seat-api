package me.mateusferreira.confirmed_seat_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.model.dto.request.UserCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.UserCreatedResponse;
import me.mateusferreira.confirmed_seat_api.service.UserService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

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
    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public UserCreatedResponse register(@Valid @RequestBody UserCreateRequest request) {
        return userService.register(request);
    }
}
