package com.falcon.affiliatetracker.repository;

import com.falcon.affiliatetracker.dto.response.VendasPorPlataformaDTO;
import com.falcon.affiliatetracker.dto.response.VendasPorProdutoDTO;
import com.falcon.affiliatetracker.models.Venda;
import com.falcon.affiliatetracker.models.enums.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long>{

    List<Venda> findByDataVendaBetween(LocalDate dataInicial, LocalDate dataFinal);

    List<Venda> findByOrigem(Plataforma origem);

    List<Venda> findByProdutoId(Long id);

    Long countByDataVendaBetween(LocalDate dataIncial, LocalDate dataFinal);

    @Query("SELECT v FROM Venda v WHERE v.origem = :origem AND v.dataVenda BETWEEN :dataInicial AND :dataFinal ORDER BY v.dataVenda DESC")
    List<Venda> buscarVendas (@Param("origem") Plataforma origem, @Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);

    @Query("SELECT COALESCE(SUM(v.valorComissao), 0) FROM Venda v")
    BigDecimal somaComissao();

    @Query("SELECT COALESCE(SUM(v.valorProduto), 0) FROM Venda v")
    BigDecimal somaVendas();

    @Query("SELECT COUNT(v) FROM Venda v")
    Long quantidadeVenda();

    @Query("SELECT COALESCE(AVG(v.valorComissao), 0.0 ) FROM Venda v")
    Double mediaComissao();

    @Query("SELECT COALESCE(SUM(v.valorComissao), 0) FROM Venda v WHERE v.dataVenda BETWEEN :dataInicial AND :dataFinal")
    BigDecimal somarPorPeriodo(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);

    @Query("SELECT new com.falcon.affiliatetracker.dto.response.VendasPorProdutoDTO(p.nome, COUNT(v), SUM(v.valorComissao)) "
            + "FROM Venda v JOIN v.produto p GROUP BY p.id, p.nome ORDER BY SUM(v.valorComissao) DESC")
    List<VendasPorProdutoDTO> vendasPorProduto();

    @Query("SELECT new com.falcon.affiliatetracker.dto.response.VendasPorPlataformaDTO(v.origem, COUNT(v), COALESCE(SUM(v.valorComissao), 0)) "
            + "FROM Venda v GROUP BY v.origem ORDER BY COALESCE(SUM(v.valorComissao), 0) DESC")
    List<VendasPorPlataformaDTO> vendasPorPlataforma();


}
