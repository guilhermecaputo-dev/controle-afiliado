package com.falcon.affiliatetracker.repository;

import com.falcon.affiliatetracker.dto.response.CliquesPorPlataformaDTO;
import com.falcon.affiliatetracker.dto.response.MelhorDesempenhoConteudoDTO;
import com.falcon.affiliatetracker.models.Clique;
import com.falcon.affiliatetracker.models.enums.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CliqueRepository extends JpaRepository<Clique, Long> {

    Long countByLinkAfiliadoId(Long id);

    Long countByOrigem(Plataforma origem);

    @Query("SELECT COUNT(c) FROM Clique c")
    Long totalDeCliques();

    @Query("SELECT new com.falcon.affiliatetracker.dto.response.CliquesPorPlataformaDTO(c.origem, COUNT(c)) "
            + "FROM Clique c GROUP BY c.origem ORDER BY COUNT(c) DESC")
    List<CliquesPorPlataformaDTO> quantidadeDeCliquePlataforma();

    @Query("SELECT new com.falcon.affiliatetracker.dto.response.MelhorDesempenhoConteudoDTO(cont.titulo, COUNT(c)) " +
            "FROM Clique c " +
            "JOIN c.linkAfiliado l " +
            "JOIN l.conteudo cont " +
            "GROUP BY cont.id, cont.titulo " +
            "ORDER BY COUNT(c) DESC")
    List<MelhorDesempenhoConteudoDTO> listarDesempenho();
}
