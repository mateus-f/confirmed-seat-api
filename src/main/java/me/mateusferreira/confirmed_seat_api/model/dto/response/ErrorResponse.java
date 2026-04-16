package me.mateusferreira.confirmed_seat_api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Schema(description = "Estrutura padrão para respostas de erro da API")
@JsonInclude(NON_NULL)
public record ErrorResponse(

        @Schema(description = "Data e hora da ocorrência do erro", example = "2026-04-16T12:00:00")
        LocalDateTime timestamp,

        @Schema(description = "Código de status HTTP", example = "400")
        int status,

        @Schema(description = "Título curto do erro", example = "Bad Request")
        String error,

        @Schema(description = "Mensagem detalhada sobre o problema", example = "Erro de validação nos campos enviados")
        String message,

        @Schema(description = "Mapa contendo detalhes específicos de cada campo (se houver)",
                example = "{\"email\": \"E-mail inválido.\", \"password\": \"Senha muito curta.\"}")
        Map<String, String> details
) {
}
