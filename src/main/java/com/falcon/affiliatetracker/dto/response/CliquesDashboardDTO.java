package com.falcon.affiliatetracker.dto.response;

import java.util.List;

public record CliquesDashboardDTO(
        Long totalCliques,
        List<CliquesPorPlataformaDTO> porPlataforma
) {
}
