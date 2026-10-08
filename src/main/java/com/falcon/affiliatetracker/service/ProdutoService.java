package com.falcon.affiliatetracker.service;

import com.falcon.affiliatetracker.dto.request.ProdutoRequestDTO;
import com.falcon.affiliatetracker.dto.response.ProdutoResponseDTO;
import com.falcon.affiliatetracker.exception.ResourceNotFoundException;
import com.falcon.affiliatetracker.models.Produto;
import com.falcon.affiliatetracker.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    private ProdutoResponseDTO toResponse(Produto p) {
        return new ProdutoResponseDTO(
                p.getId(),
                p.getNome(),
                p.getDescricao(),
                p.getPreco(),
                p.getCategoria(),
                p.getAvaliacao(),
                p.getUrlMercadoLivre(),
                p.isAtivo(),
                p.getDataCadastro());
    }

    private Produto buscarOuFalhar(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto){
        Produto produto = new Produto();

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setCategoria(dto.categoria());
        produto.setAvaliacao(dto.avaliacao());
        produto.setUrlMercadoLivre(dto.urlMercadoLivre());
        produto.setAtivo(true);
        produto.setDataCadastro(LocalDate.now());

        Produto salvo = produtoRepository.save(produto);

        return toResponse(salvo);
    }

    public List<ProdutoResponseDTO> listarProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(Long id){
        Produto produto = buscarOuFalhar(id);

        return toResponse(produto);
    }

    public void deletarPorId(Long id){

        produtoRepository.delete(buscarOuFalhar(id));
    }

    public ProdutoResponseDTO atualizarPorId(Long id, ProdutoRequestDTO dto){
        Produto produto = buscarOuFalhar(id);

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setCategoria(dto.categoria());
        produto.setAvaliacao(dto.avaliacao());
        produto.setUrlMercadoLivre(dto.urlMercadoLivre());

        Produto salvo = produtoRepository.save(produto);

        return toResponse(salvo);
    }

}
