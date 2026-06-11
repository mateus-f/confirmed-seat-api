package me.mateusferreira.confirmed_seat_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.mateusferreira.confirmed_seat_api.model.dto.request.CreateBookingRequest;
import me.mateusferreira.confirmed_seat_api.model.dto.response.BookingDetailResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.BookingSummaryResponse;
import me.mateusferreira.confirmed_seat_api.model.dto.response.CreateBookingResponse;
import me.mateusferreira.confirmed_seat_api.service.BookingService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
@Tag(name = "Reservas", description = "Endpoints para criação, consulta e cancelamento de reservas")
@SecurityRequirement(name = "bearerAuth")
public class BookingController {

    private final BookingService bookingService;

    @Operation(
            summary = "Criar uma nova reserva",
            description = "Efetua a reserva de uma vaga em um evento para o usuário autenticado. Valida a disponibilidade de vagas em tempo real."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados malformados)", content = @Content),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Não há vagas disponíveis ou o usuário já possui reserva para este evento", content = @Content)
    })
    @PostMapping
    public CreateBookingResponse createBooking(@Valid @RequestBody CreateBookingRequest request) {
        return bookingService.create(request);
    }

    @Operation(
            summary = "Listar minhas reservas",
            description = "Retorna uma lista paginada com o resumo de todas as reservas efetuadas pelo usuário autenticado."
    )
    @GetMapping("/me")
    public ResponseEntity<Page<BookingSummaryResponse>> listMyBookings(
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        Page<BookingSummaryResponse> bookings = bookingService.findAllMyBookings(pageable);

        return ResponseEntity.ok(bookings);
    }

    @Operation(
            summary = "Buscar reserva pelo código de confirmação",
            description = "Retorna os detalhes completos de uma reserva específica utilizando o UUID de confirmação."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma reserva encontrada com o código fornecido", content = @Content)
    })
    @GetMapping("/{confirmationCode}")
    public BookingDetailResponse getBookingByConfirmationCode(@PathVariable UUID confirmationCode) {
        return bookingService.getBookingByConfirmationCode(confirmationCode);
    }

    @Operation(
            summary = "Cancelar reserva",
            description = "Modifica o status da reserva para cancelado e libera a vaga correspondente no evento. **Operação idempotente.**"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva cancelada com sucesso e dados atualizados retornados"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada", content = @Content),
            @ApiResponse(responseCode = "400", description = "A reserva não pode ser cancelada (ex: evento já ocorreu)", content = @Content)
    })
    @DeleteMapping("/{confirmationCode}")
    public BookingDetailResponse cancelBookingByConfirmationCode(@PathVariable UUID confirmationCode) {
        return bookingService.cancelBookingByConfirmationCode(confirmationCode);
    }
}
