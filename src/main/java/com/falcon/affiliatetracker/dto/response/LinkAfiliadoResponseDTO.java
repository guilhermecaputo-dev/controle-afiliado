package com.falcon.affiliatetracker.dto.response;

public record LinkAfiliadoResponseDTO(
        Long id,
        String url,
        String codigo,
        boolean ativo,
        Long produtoId,
        Long conteudoId,
        String produtoNome,
        String conteudoTitulo) {
}
