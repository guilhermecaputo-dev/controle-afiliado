package com.falcon.affiliatetracker.dto.request;

import com.falcon.affiliatetracker.models.enums.Plataforma;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

public record ConteudoRequestDTO(
        @NotBlank(message = "O titulo não pode ser vazio") @Size(max = 75, message = "O titulo deve ter no máximo 75 caracteres")String titulo,
        @NotBlank(message = "A descrição não pode ser vazia") String descricao,
        @NotNull(message = "A plataforma não pode ser nula") Plataforma plataforma,
        @NotBlank(message = "A url não pode ser vazia") @URL(message = "A URL deve ser válida") String url,
        @NotNull(message = "A data não pode ser nula") @PastOrPresent(message = "A data de publicação não pode ser futura") LocalDate dataPublicacao,
        @NotNull(message = "As visualizacoes não podem ser nulas") @PositiveOrZero(message = "O minimo de visualizações é 0") Long visualizacoes,
        @NotNull(message = "O produto é obrigatório") Long produtoId) {
}
