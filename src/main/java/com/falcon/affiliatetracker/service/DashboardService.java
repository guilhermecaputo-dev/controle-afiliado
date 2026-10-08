package com.falcon.affiliatetracker.service;

import com.falcon.affiliatetracker.dto.response.DashboardResumoDTO;
import com.falcon.affiliatetracker.repository.CliqueRepository;
import com.falcon.affiliatetracker.repository.ConteudoRepository;
import com.falcon.affiliatetracker.repository.ProdutoRepository;
import com.falcon.affiliatetracker.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DashboardService {
    private final ProdutoRepository produtoRepository;
    private final ConteudoRepository conteudoRepository;
    private final VendaRepository vendaRepository;
    private final CliqueRepository cliqueRepository;

    private Long buscarQuantidadeProdutos() {
        return produtoRepository.count();
    }

    private Long buscarQuantidadeConteudo(){
        return conteudoRepository.count();
    }

    private Long buscarQuantidadeVendas(){
        return vendaRepository.count();
    }

    private Long buscarQuantidadeCliques(){
        return cliqueRepository.count();
    }

    private BigDecimal somaFaturamento(){
        return vendaRepository.somaVendas();
    }

    private BigDecimal somaComissao(){
        return vendaRepository.somaComissao();
    }

    private BigDecimal calcularTaxaConversao(Long totalVendas, Long totalCliques) {

        BigDecimal vendas = BigDecimal.valueOf(totalVendas);
        BigDecimal cliques = BigDecimal.valueOf(totalCliques);
        BigDecimal cem = BigDecimal.valueOf(100);

        if (cliques.compareTo(BigDecimal.ZERO) == 0){
            return BigDecimal.ZERO;
        }

        return vendas.multiply(cem).divide(cliques, 2, RoundingMode.HALF_UP);
    }

    public DashboardResumoDTO gerarResumo(){
        Long vendas = buscarQuantidadeVendas();
        Long cliques = buscarQuantidadeCliques();

        return new DashboardResumoDTO(
                buscarQuantidadeProdutos(),
                buscarQuantidadeConteudo(),
                cliques,
                vendas,
                somaFaturamento(),
                somaComissao(),
                calcularTaxaConversao(vendas, cliques)
        );
    }
}
