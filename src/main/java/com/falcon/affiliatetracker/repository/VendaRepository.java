package com.falcon.affiliatetracker.repository;

import com.falcon.affiliatetracker.models.Venda;
import com.falcon.affiliatetracker.models.enums.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long>{

    List<Venda> findByDataVendaBetween(LocalDate dataInicial, LocalDate dataFinal);

    List<Venda> findByOrigem(Plataforma origem);

    List<Venda> findByProdutoId(Long id);
}
