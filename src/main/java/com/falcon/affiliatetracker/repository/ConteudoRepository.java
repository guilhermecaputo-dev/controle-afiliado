package com.falcon.affiliatetracker.repository;

import com.falcon.affiliatetracker.models.Conteudo;
import com.falcon.affiliatetracker.models.enums.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {

    List<Conteudo> findByPlataforma(Plataforma plataforma);

    List<Conteudo> findByProdutoId(Long id);
}
