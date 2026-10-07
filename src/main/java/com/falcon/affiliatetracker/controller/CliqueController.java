package com.falcon.affiliatetracker.controller;

import com.falcon.affiliatetracker.dto.request.CliqueRequestDTO;
import com.falcon.affiliatetracker.dto.response.CliqueResponseDTO;
import com.falcon.affiliatetracker.service.CliqueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliques")
@AllArgsConstructor
public class CliqueController {
    private final CliqueService cliqueService;

    @GetMapping
    public ResponseEntity<List<CliqueResponseDTO>> listarCliques(){
        return ResponseEntity.ok().body(cliqueService.listarCliques());
    }

    @PostMapping
    public ResponseEntity<CliqueResponseDTO> salvar(@Valid @RequestBody CliqueRequestDTO dto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(cliqueService.registrar(dto));
    }
}
