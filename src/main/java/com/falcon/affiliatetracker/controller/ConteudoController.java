package com.falcon.affiliatetracker.controller;

import com.falcon.affiliatetracker.dto.request.ConteudoRequestDTO;
import com.falcon.affiliatetracker.dto.response.ConteudoResponseDTO;
import com.falcon.affiliatetracker.service.ConteudoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conteudos")
@RequiredArgsConstructor
public class ConteudoController {
    private final ConteudoService conteudoService;

    @PostMapping
    public ResponseEntity<ConteudoResponseDTO> salvar(@Valid @RequestBody ConteudoRequestDTO dto ){
        return ResponseEntity.status(HttpStatus.CREATED).body(conteudoService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ConteudoResponseDTO>> listarTodos(){
        return ResponseEntity.ok().body(conteudoService.listarConteudo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConteudoResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(conteudoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        conteudoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConteudoResponseDTO> atualizarPorId(@PathVariable Long id, @Valid @RequestBody ConteudoRequestDTO dto){
        return ResponseEntity.ok().body(conteudoService.atualizarPorId(id, dto));
    }
}
