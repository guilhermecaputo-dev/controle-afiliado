package com.falcon.affiliatetracker.service;

import com.falcon.affiliatetracker.dto.request.ConteudoRequestDTO;
import com.falcon.affiliatetracker.dto.response.ConteudoResponseDTO;
import com.falcon.affiliatetracker.exception.BusinessException;
import com.falcon.affiliatetracker.exception.ResourceNotFoundException;
import com.falcon.affiliatetracker.models.Conteudo;
import com.falcon.affiliatetracker.models.Produto;
import com.falcon.affiliatetracker.repository.ConteudoRepository;
import com.falcon.affiliatetracker.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConteudoService {
    private final ConteudoRepository conteudoRepository;
    private final ProdutoRepository produtoRepository;

    private Produto buscarProdutoOuFalhar(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    private Conteudo buscarConteudoOuFalhar(Long id){
        return conteudoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conteudo não encontrado"));
    }

    private ConteudoResponseDTO toResponse(Conteudo conteudo){
        return new ConteudoResponseDTO(
                conteudo.getId(),
                conteudo.getTitulo(),
                conteudo.getDescricao(),
                conteudo.getPlataforma(),
                conteudo.getUrl(),
                conteudo.getDataPublicacao(),
                conteudo.getVisualizacoes(),
                conteudo.getProduto().getId(),
                conteudo.getProduto().getNome()
        );
    }

    public ConteudoResponseDTO salvar(ConteudoRequestDTO dto){
        Produto produto = buscarProdutoOuFalhar(dto.produtoId());

        if (!produto.isAtivo()){
            throw new BusinessException("O produto está inativo");
        }

        Conteudo conteudo = new Conteudo();

        conteudo.setTitulo(dto.titulo());
        conteudo.setDescricao(dto.descricao());
        conteudo.setPlataforma(dto.plataforma());
        conteudo.setUrl(dto.url());
        conteudo.setDataPublicacao(dto.dataPublicacao());
        conteudo.setVisualizacoes(dto.visualizacoes());
        conteudo.setProduto(produto);

        Conteudo salvo = conteudoRepository.save(conteudo);

        return toResponse(salvo);
    }

    public List<ConteudoResponseDTO> listarConteudo(){
        List<Conteudo> conteudos = conteudoRepository.findAll();

        return conteudos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ConteudoResponseDTO buscarPorId(Long id){
        Conteudo conteudo = buscarConteudoOuFalhar(id);

        return toResponse(conteudo);
    }

    public void deletarPorId(Long id){
        conteudoRepository.delete(buscarConteudoOuFalhar(id));
    }

    public ConteudoResponseDTO atualizarPorId(Long id, ConteudoRequestDTO dto){
        Conteudo conteudo = buscarConteudoOuFalhar(id);

        conteudo.setTitulo(dto.titulo());
        conteudo.setDescricao(dto.descricao());
        conteudo.setPlataforma(dto.plataforma());
        conteudo.setUrl(dto.url());
        conteudo.setDataPublicacao(dto.dataPublicacao());
        conteudo.setVisualizacoes(dto.visualizacoes());

        Conteudo salvo = conteudoRepository.save(conteudo);

        return toResponse(salvo);
    }

}
