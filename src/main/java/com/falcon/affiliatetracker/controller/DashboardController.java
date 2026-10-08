package com.falcon.affiliatetracker.controller;

import com.falcon.affiliatetracker.dto.response.DashboardResumoDTO;
import com.falcon.affiliatetracker.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/resumo")
    public ResponseEntity<DashboardResumoDTO> resumir(){
        return ResponseEntity.ok().body(dashboardService.gerarResumo());
    }
}
