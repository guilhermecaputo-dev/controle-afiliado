package com.falcon.affiliatetracker.controller;

import com.falcon.affiliatetracker.dto.request.ProdutoRequestDTO;
import com.falcon.affiliatetracker.dto.response.ProdutoResponseDTO;
import com.falcon.affiliatetracker.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@Valid @RequestBody ProdutoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos(){
        return ResponseEntity.ok().body(produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(produtoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarPorId(@PathVariable Long id,@Valid @RequestBody ProdutoRequestDTO dto){
        return ResponseEntity.ok().body(produtoService.atualizarPorId(id,dto));
    }
}
