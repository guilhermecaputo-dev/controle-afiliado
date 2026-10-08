package com.falcon.affiliatetracker.repository;

import com.falcon.affiliatetracker.models.Produto;
import com.falcon.affiliatetracker.models.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoria(Categoria categoria);

    List<Produto> findByAtivoTrue();

    List<Produto> findByNomeContainingIgnoreCase(String texto);

    List<Produto> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);

    @Query("SELECT p FROM Produto p WHERE p.ativo = true AND p.categoria = :categoria ORDER BY p.preco")
    List<Produto> selecionarAtivosCategoria(@Param("categoria") Categoria categoria);
}
