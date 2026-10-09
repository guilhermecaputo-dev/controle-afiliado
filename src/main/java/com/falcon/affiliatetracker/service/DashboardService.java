package com.falcon.affiliatetracker.service;

import com.falcon.affiliatetracker.dto.response.*;
import com.falcon.affiliatetracker.exception.BusinessException;
import com.falcon.affiliatetracker.repository.CliqueRepository;
import com.falcon.affiliatetracker.repository.ConteudoRepository;
import com.falcon.affiliatetracker.repository.ProdutoRepository;
import com.falcon.affiliatetracker.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

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

    private BigDecimal calcularMediaComissao(BigDecimal comissao, Long quantidadeVendas){
        if (quantidadeVendas <= 0){
            return BigDecimal.ZERO;
        }
        return comissao.divide(BigDecimal.valueOf(quantidadeVendas), 2, RoundingMode.HALF_UP);
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

    public BigDecimal somaComissaoPeriodo(LocalDate dataInicial, LocalDate dataFinal){
        if (dataInicial.isAfter(dataFinal)){
            throw new BusinessException("A data inicial não pode ser posterior a final");
        }

        return vendaRepository.somarPorPeriodo(dataInicial, dataFinal);
    }

    public List<VendasPorProdutoDTO> vendasPorProduto(int limite){
        if (limite < 1){
            throw new BusinessException("O limite deve ser maior que 0”");
        }
        return vendaRepository.vendasPorProduto().stream().limit(limite).toList();
    }

    public List<VendasPorPlataformaDTO> vendasPorPlataforma(){
        return vendaRepository.vendasPorPlataforma();
    }

    public List<MelhorDesempenhoConteudoDTO> listarDesempenho(int limite){
        if (limite < 1){
            throw new BusinessException("O limite deve ser maior que 0”");
        }
        return cliqueRepository.listarDesempenho().stream().limit(limite).toList();
    }

    public CliquesDashboardDTO cliquesPorPlataforma(){
        Long totalCliques = cliqueRepository.count();
        List<CliquesPorPlataformaDTO> dadosPlataforma = cliqueRepository.quantidadeDeCliquePlataforma();
        return new CliquesDashboardDTO(totalCliques, dadosPlataforma);

    }

    public ComissaoPeriodoDTO comissaoPorPeriodo(LocalDate dataInicial, LocalDate dataFinal){
        BigDecimal comissao = somaComissaoPeriodo(dataInicial, dataFinal);
        Long vendas = vendaRepository.countByDataVendaBetween(dataInicial, dataFinal);
        BigDecimal mediaComissao = calcularMediaComissao(comissao, vendas);
        return new ComissaoPeriodoDTO(
                dataInicial,
                dataFinal,
                comissao,
                vendas,
                mediaComissao
        );
    }

}
