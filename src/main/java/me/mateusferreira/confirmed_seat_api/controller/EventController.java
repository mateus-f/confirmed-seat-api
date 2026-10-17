package me.mateusferreira.confirmed_seat_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.model.dto.request.EventCreateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.request.EventUpdateRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.*;
import me.mateusferreira.confirmed_seat_api.service.EventService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
@Tag(name = "Eventos", description = "Endpoints para criação, consulta e gestão de eventos")
@SecurityRequirement(name = "bearerAuth")
public class EventController {

    private final EventService eventService;

    @Operation(
            summary = "Criar um novo evento",
            description = "Cadastra um novo evento no sistema. **Acesso restrito a administradores (ROLE_ADMIN).**"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Credenciais inválidas",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Não autorizado: token ausente, inválido ou expirado",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado: o usuário não possui perfil de ADMIN",
                    content = @Content),
    })
    @PostMapping
    @ResponseStatus(CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public EventCreateResponse create(@Valid @RequestBody EventCreateRequest request) {
        return eventService.create(request);
    }

    @Operation(
            summary = "Listar eventos disponíveis",
            description = "Retorna uma lista paginada de todos os eventos ativos e com vagas disponíveis no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos listados com sucesso")
    })
    @GetMapping
    public ResponseEntity<PageResponse<EventSummaryResponse>> listAvailableEvents(
            @ParameterObject @PageableDefault(size = 10, sort = "eventDate") Pageable pageable) {
        PageResponse<EventSummaryResponse> event = eventService.findAllAvailable(pageable);

        return ResponseEntity.ok(event);
    }

    @Operation(
            summary = "Buscar evento pelo ID",
            description = "Retorna os detalhes completos de um evento específico, incluindo o balanço de vagas restantes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public EventDetailResponse getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @Operation(
            summary = "Atualizar dados do evento",
            description = "Permite modificar as informações de um evento existente (título, descrição, local, etc.). **Acesso restrito a administradores (ROLE_ADMIN).**"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de atualização inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EventDetailResponse updateEvent(@PathVariable Long id, @Valid @RequestBody EventUpdateRequest request) {
        return eventService.update(id, request);
    }

    @Operation(
            summary = "Excluir um evento",
            description = "Remove um evento do sistema com base no ID fornecido. **Acesso restrito a administradores (ROLE_ADMIN).**"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evento excluído com sucesso (No Content)", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        return eventService.delete(id);
    }
}
