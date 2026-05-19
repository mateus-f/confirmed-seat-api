package me.mateusferreira.confirmed_seat_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.model.dto.request.EventCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.EventCreateResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.EventSummaryResponse;
import me.mateusferreira.confirmed_seat_api.service.EventService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
@Tag(name = "Eventos", description = "Criação e gestão de eventos")
@SecurityRequirement(name = "bearerAuth")
public class EventController {

    private final EventService eventService;

    @Operation(
            summary = "Criar um novo evento",
            description = "Cadastra um novo evento no sistema. **Acesso restrito a administradores (ROLE_ADMIN).**"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento criado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado: o usuário não possui perfil de ADMIN"),
            @ApiResponse(responseCode = "401", description = "Não autorizado: token ausente ou inválido"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados do evento")
    })
    @PostMapping
    @ResponseStatus(CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public EventCreateResponse create(@Valid @RequestBody EventCreateRequest request) {
        return eventService.create(request);
    }

    @GetMapping
    public ResponseEntity<Page<EventSummaryResponse>> listAvailableEvents(
            @ParameterObject @PageableDefault(size = 10, sort = "eventDate") Pageable pageable) {
        Page<EventSummaryResponse> event = eventService.findAllAvailable(pageable);

        return ResponseEntity.ok(event);
    }

    @GetMapping("/{id}")
    public EventSummaryResponse getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
}
