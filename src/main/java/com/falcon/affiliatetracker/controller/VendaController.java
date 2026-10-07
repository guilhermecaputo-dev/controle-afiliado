package com.falcon.affiliatetracker.controller;

import com.falcon.affiliatetracker.dto.request.VendaRequestDTO;
import com.falcon.affiliatetracker.dto.response.VendaResponseDTO;
import com.falcon.affiliatetracker.service.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {
    private final VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaResponseDTO> registrar(@Valid @RequestBody VendaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.registrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> listarVendas(){
        return ResponseEntity.ok().body(vendaService.listarVendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(vendaService.buscarPorId(id));
    }
}
