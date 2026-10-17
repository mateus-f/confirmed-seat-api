package me.mateusferreira.confirmed_seat_api.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(description = "Objeto de resposta paginada genérica")
public record PageResponse<T>(

        @Schema(description = "Lista de elementos contidos na página atual")
        List<T> content,

        @Schema(description = "Metadados da paginação")
        PageMetadata page
) {
    @Schema(description = "Informações detalhadas sobre a paginação")
    public record PageMetadata(

            @Schema(description = "Quantidade de elementos por página", example = "10")
            int size,

            @Schema(description = "Número da página atual (começando em 0)", example = "0")
            int number,

            @Schema(description = "Total geral de elementos encontrados na base de dados", example = "45")
            long totalElements,

            @Schema(description = "Total de páginas disponíveis", example = "5")
            int totalPages,

            @Schema(description = "Indica se esta é a primeira página", example = "true")
            boolean first,

            @Schema(description = "Indica se esta é a última página", example = "false")
            boolean last
    ) {
    }

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                new PageMetadata(
                        page.getSize(),
                        page.getNumber(),
                        page.getTotalElements(),
                        page.getTotalPages(),
                        page.isFirst(),
                        page.isLast()
                )
        );
    }

}
