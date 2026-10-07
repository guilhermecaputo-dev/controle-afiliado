package com.falcon.affiliatetracker.service;

import com.falcon.affiliatetracker.dto.request.VendaRequestDTO;
import com.falcon.affiliatetracker.dto.response.VendaResponseDTO;
import com.falcon.affiliatetracker.exception.ResourceNotFoundException;
import com.falcon.affiliatetracker.models.Produto;
import com.falcon.affiliatetracker.models.Venda;
import com.falcon.affiliatetracker.repository.ProdutoRepository;
import com.falcon.affiliatetracker.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendaService {
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    private Produto buscarProdutoOuFalhar(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    private Venda buscarVendaOuFalhar(Long id){
        return vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));
    }

    private VendaResponseDTO toResponse(Venda venda){
        return new VendaResponseDTO(
                venda.getId(),
                venda.getValorComissao(),
                venda.getProduto().getNome(),
                venda.getProduto().getId(),
                venda.getDataVenda(),
                venda.getValorProduto(),
                venda.getPercentualComissao(),
                venda.getOrigem()
        );
    }

    public VendaResponseDTO registrar(VendaRequestDTO dto){
        Produto produto = buscarProdutoOuFalhar(dto.produtoId());

        BigDecimal divisor = new BigDecimal("100");
        BigDecimal valorComissao = dto.valorProduto().multiply(dto.percentualComissao()).divide(divisor, 2, RoundingMode.HALF_UP);

        Venda venda = new Venda();

        venda.setDataVenda(dto.dataVenda());
        venda.setValorProduto(dto.valorProduto());
        venda.setPercentualComissao(dto.percentualComissao());
        venda.setValorComissao(valorComissao);
        venda.setOrigem(dto.origem());
        venda.setProduto(produto);

        Venda salvo = vendaRepository.save(venda);

        return toResponse(salvo);
    }

    public List<VendaResponseDTO> listarVendas(){
        List<Venda> vendas = vendaRepository.findAll();

        return vendas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public VendaResponseDTO buscarPorId(Long id){
        Venda venda = buscarVendaOuFalhar(id);

        return toResponse(venda);
    }
}
