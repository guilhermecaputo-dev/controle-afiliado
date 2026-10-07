package com.falcon.affiliatetracker.dto.response;

import com.falcon.affiliatetracker.models.enums.Categoria;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Categoria categoria,
        BigDecimal avaliacao,
        String urlMercadoLivre,
        boolean ativo,
        LocalDate dataCadastro) {
}
