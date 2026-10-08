package com.falcon.affiliatetracker.repository;

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
}
