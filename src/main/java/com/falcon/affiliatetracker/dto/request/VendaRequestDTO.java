package com.falcon.affiliatetracker.dto.request;

import com.falcon.affiliatetracker.models.enums.Plataforma;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VendaRequestDTO(
        @NotNull(message = "O id do produto não pode ser nulo") Long produtoId,
        @NotNull(message = "A origem não pode ser vazia") Plataforma origem,
        @NotNull(message = "A data da venda não pode ser nula") @PastOrPresent(message = "A data da venda não pode ser posterior a data de hoje") LocalDate dataVenda,
        @NotNull(message = "O valor do produto não pode ser nulo") @Positive(message = "O valor do produto deve ser maior que 0") BigDecimal valorProduto,
        @NotNull(message = "O percentual de comissão não pode ser nulo") @DecimalMin(value = "0.0", inclusive = false, message = "O percentual de comissão deve ser maior que 0") @DecimalMax(value = "100", message = "O percentual máximo de comissão é 100") BigDecimal percentualComissao
) {
}
