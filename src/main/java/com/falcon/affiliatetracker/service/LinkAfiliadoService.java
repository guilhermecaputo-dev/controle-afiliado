package com.falcon.affiliatetracker.service;

import com.falcon.affiliatetracker.dto.request.LinkAfiliadoRequestDTO;
import com.falcon.affiliatetracker.dto.response.LinkAfiliadoResponseDTO;
import com.falcon.affiliatetracker.exception.BusinessException;
import com.falcon.affiliatetracker.exception.ResourceNotFoundException;
import com.falcon.affiliatetracker.models.Conteudo;
import com.falcon.affiliatetracker.models.LinkAfiliado;
import com.falcon.affiliatetracker.models.Produto;
import com.falcon.affiliatetracker.repository.ConteudoRepository;
import com.falcon.affiliatetracker.repository.LinkAfiliadoRepository;
import com.falcon.affiliatetracker.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LinkAfiliadoService {
    private final LinkAfiliadoRepository linkAfiliadoRepository;
    private final ProdutoRepository produtoRepository;
    private final ConteudoRepository conteudoRepository;

    private Produto buscarProdutoOuFalhar(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    private Conteudo buscarConteudoOuFalhar(Long id){
        return conteudoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conteúdo não encontrado"));
    }

    private LinkAfiliado buscarLinkAfiliadoOuFalhar(Long id){
        return linkAfiliadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Link de afiliado não encontrado"));
    }

    private LinkAfiliadoResponseDTO toResponse(LinkAfiliado link){
        return new LinkAfiliadoResponseDTO(
                link.getId(),
                link.getUrl(),
                link.getCodigo(),
                link.isAtivo(),
                link.getProduto().getId(),
                link.getConteudo().getId(),
                link.getProduto().getNome(),
                link.getConteudo().getTitulo()
        );
    }

    public LinkAfiliadoResponseDTO salvar(LinkAfiliadoRequestDTO dto){
        Produto produto = buscarProdutoOuFalhar(dto.produtoId());
        Conteudo conteudo = buscarConteudoOuFalhar(dto.conteudoId());

        if (!produto.isAtivo()){
            throw new BusinessException("O produto não pode estar inativo");
        }

        if (!conteudo.getProduto().getId().equals(produto.getId())){
            throw new BusinessException("O produto precisa ser o mesmo do conteúdo");
        }

        if (linkAfiliadoRepository.existsByCodigo(dto.codigo())){
            throw new BusinessException("O código do link já existe");
        }

        LinkAfiliado link = new LinkAfiliado();

        link.setUrl(dto.url());
        link.setCodigo(dto.codigo());
        link.setAtivo(true);
        link.setProduto(produto);
        link.setConteudo(conteudo);

        LinkAfiliado salvo = linkAfiliadoRepository.save(link);

        return toResponse(salvo);
    }

    public List<LinkAfiliadoResponseDTO> listarLinks(){
        List<LinkAfiliado> links = linkAfiliadoRepository.findAll();

        return links.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public LinkAfiliadoResponseDTO buscarPorId(Long id){
        LinkAfiliado linkAfiliado = buscarLinkAfiliadoOuFalhar(id);

        return toResponse(linkAfiliado);
    }

    public void deletarPorId(Long id){
        linkAfiliadoRepository.delete(buscarLinkAfiliadoOuFalhar(id));
    }

    public LinkAfiliadoResponseDTO atualizarPorId(Long id, LinkAfiliadoRequestDTO dto){
        LinkAfiliado link = buscarLinkAfiliadoOuFalhar(id);

        link.setUrl(dto.url());

        if (!link.getCodigo().equals(dto.codigo())){
            if (linkAfiliadoRepository.existsByCodigo(dto.codigo())){
                throw new BusinessException("O código informado já existe");
            }
            link.setCodigo(dto.codigo());
        }

        LinkAfiliado salvo = linkAfiliadoRepository.save(link);

        return toResponse(salvo);
    }

}
