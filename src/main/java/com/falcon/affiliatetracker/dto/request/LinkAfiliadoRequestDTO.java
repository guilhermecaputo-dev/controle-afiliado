package com.falcon.affiliatetracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record LinkAfiliadoRequestDTO(
        @NotBlank(message = "A URL não pode ser vazia") @URL(message = "A url deve ser válida") String url,
        @NotBlank(message = "O código não pode ser nulo") String codigo,
        @NotNull(message = "O produto não pode ser nulo") Long produtoId,
        @NotNull(message = "O conteúdo não pode ser nulo")Long conteudoId) {
}
