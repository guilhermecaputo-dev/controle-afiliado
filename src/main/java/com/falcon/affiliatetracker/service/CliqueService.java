package com.falcon.affiliatetracker.service;

import com.falcon.affiliatetracker.dto.request.CliqueRequestDTO;
import com.falcon.affiliatetracker.dto.response.CliqueResponseDTO;
import com.falcon.affiliatetracker.exception.BusinessException;
import com.falcon.affiliatetracker.exception.ResourceNotFoundException;
import com.falcon.affiliatetracker.models.Clique;
import com.falcon.affiliatetracker.models.LinkAfiliado;
import com.falcon.affiliatetracker.repository.CliqueRepository;
import com.falcon.affiliatetracker.repository.LinkAfiliadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CliqueService {
    private final LinkAfiliadoRepository linkAfiliadoRepository;
    private final CliqueRepository cliqueRepository;

    private LinkAfiliado buscarLinkOuFalhar(Long id){
        return linkAfiliadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Link não encontrado"));
    }

    private CliqueResponseDTO toResponse(Clique clique){
        return new CliqueResponseDTO(
                clique.getId(),
                clique.getDataHora(),
                clique.getOrigem(),
                clique.getLinkAfiliado().getId(),
                clique.getLinkAfiliado().getCodigo()
        );
    }

    public CliqueResponseDTO registrar(CliqueRequestDTO dto){
        LinkAfiliado linkAfiliado = buscarLinkOuFalhar(dto.linkAfiliadoId());

        if (!linkAfiliado.isAtivo()){
            throw new BusinessException("Link não está ativo");
        }

        Clique clique = new Clique();

        clique.setOrigem(dto.origem());
        clique.setLinkAfiliado(linkAfiliado);
        clique.setDataHora(LocalDateTime.now());

        Clique salvo = cliqueRepository.save(clique);

        return toResponse(salvo);
    }

    public List<CliqueResponseDTO> listarCliques(){
        List<Clique> cliques = cliqueRepository.findAll();

        return cliques.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
