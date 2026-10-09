package com.falcon.affiliatetracker.controller;

import com.falcon.affiliatetracker.dto.response.*;
import com.falcon.affiliatetracker.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/resumo")
    public ResponseEntity<DashboardResumoDTO> resumir(){
        return ResponseEntity.ok().body(dashboardService.gerarResumo());
    }

    @GetMapping("/comissoes")
    public ResponseEntity<ComissaoPeriodoDTO> somarPorPeriodo(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal){
        return ResponseEntity.ok().body(dashboardService.comissaoPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping("/vendas-por-produto")
    public ResponseEntity<List<VendasPorProdutoDTO>> vendasPorProduto(@RequestParam(defaultValue = "10") int limite){
        return ResponseEntity.ok().body(dashboardService.vendasPorProduto(limite));
    }

    @GetMapping("/vendas-por-plataforma")
    public ResponseEntity<List<VendasPorPlataformaDTO>> vendasPorPlataforma() {
        return ResponseEntity.ok().body(dashboardService.vendasPorPlataforma());
    }

    @GetMapping("/conteudos-melhores")
    public ResponseEntity<List<MelhorDesempenhoConteudoDTO>> listarDesempenho(@RequestParam(defaultValue = "10") int limite){
        return ResponseEntity.ok().body(dashboardService.listarDesempenho(limite));
    }

    @GetMapping("/cliques")
    public ResponseEntity<CliquesDashboardDTO> cliquesPorPlataforma(){
        return ResponseEntity.ok().body(dashboardService.cliquesPorPlataforma());
    }
}
