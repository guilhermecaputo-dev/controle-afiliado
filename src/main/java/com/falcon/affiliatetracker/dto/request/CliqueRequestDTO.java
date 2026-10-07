package com.falcon.affiliatetracker.dto.request;


import com.falcon.affiliatetracker.models.enums.Plataforma;
import jakarta.validation.constraints.NotNull;

public record CliqueRequestDTO(
        @NotNull(message = "A origem do clique não pode ser nula") Plataforma origem,
        @NotNull(message = "O link não pode ser nulo") Long linkAfiliadoId
        ) {
}
