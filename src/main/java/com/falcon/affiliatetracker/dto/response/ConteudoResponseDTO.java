package com.falcon.affiliatetracker.dto.response;

import com.falcon.affiliatetracker.models.enums.Plataforma;

import java.time.LocalDate;

public record ConteudoResponseDTO(
        Long id,
        String titulo,
        String descricao,
        Plataforma plataforma,
        String url,
        LocalDate dataPublicacao,
        Long visualizacoes,
        Long produtoId,
        String produtoNome) {
}
