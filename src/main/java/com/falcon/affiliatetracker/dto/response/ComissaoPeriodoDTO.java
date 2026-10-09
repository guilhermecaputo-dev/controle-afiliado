package com.falcon.affiliatetracker.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ComissaoPeriodoDTO(
        LocalDate inicio,
        LocalDate fim,
        BigDecimal totalComissao,
        Long quantidadeVendas,
        BigDecimal mediaComissao
) {
}
