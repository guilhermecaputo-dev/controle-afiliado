package com.falcon.affiliatetracker.repository;

import com.falcon.affiliatetracker.models.Clique;
import com.falcon.affiliatetracker.models.enums.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CliqueRepository extends JpaRepository<Clique, Long> {

    Long countByLinkAfiliadoId(Long id);

    Long countByOrigem(Plataforma origem);
}
