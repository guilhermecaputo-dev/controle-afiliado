package com.falcon.affiliatetracker.controller;

import com.falcon.affiliatetracker.dto.request.LinkAfiliadoRequestDTO;
import com.falcon.affiliatetracker.dto.response.LinkAfiliadoResponseDTO;
import com.falcon.affiliatetracker.service.LinkAfiliadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
public class LinkAfiliadoController {
    private final LinkAfiliadoService linkAfiliadoSevice;

    @PostMapping
    public ResponseEntity<LinkAfiliadoResponseDTO> salvar(@Valid @RequestBody LinkAfiliadoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(linkAfiliadoSevice.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<LinkAfiliadoResponseDTO>> listarTodos(){
        return ResponseEntity.ok().body(linkAfiliadoSevice.listarLinks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkAfiliadoResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(linkAfiliadoSevice.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        linkAfiliadoSevice.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinkAfiliadoResponseDTO> atualizarPorId(@PathVariable Long id, @Valid @RequestBody LinkAfiliadoRequestDTO dto){
        return ResponseEntity.ok().body(linkAfiliadoSevice.atualizarPorId(id, dto));
    }
}
