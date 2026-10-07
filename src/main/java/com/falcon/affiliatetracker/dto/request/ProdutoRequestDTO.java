package com.falcon.affiliatetracker.dto.request;

import com.falcon.affiliatetracker.models.enums.Categoria;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        @NotBlank(message = "O nome não pode ser vazio") @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres") String nome,
        @NotBlank(message = "A descrição não pode ser vazia") String descricao,
        @NotNull(message = "O preço não pode ser nulo") @Positive(message = "O preço deve ser maior que 0") BigDecimal preco,
        @NotNull(message = "A categoria não pode ser nula") Categoria categoria,
        @NotNull(message = "A avaliação não pode ser nula") @DecimalMin(value = "0.0",message = "O minimo de avaliação deve ser 0.0") @DecimalMax(value = "5.0", message = "O máximo da avaliação deve ser 5.0") BigDecimal avaliacao,
        @NotBlank(message = "A URL não pode ser vazia") @URL(message = "Deve inserir uma URL válida") String urlMercadoLivre) {
}
