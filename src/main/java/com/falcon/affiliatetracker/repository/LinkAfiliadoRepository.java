package com.falcon.affiliatetracker.repository;

import com.falcon.affiliatetracker.models.LinkAfiliado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkAfiliadoRepository extends JpaRepository<LinkAfiliado, Long> {

    boolean existsByCodigo(String codigo);
}
