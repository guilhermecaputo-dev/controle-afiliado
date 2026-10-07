package com.falcon.affiliatetracker.models;

import com.falcon.affiliatetracker.models.enums.Plataforma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "cliques")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Clique {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dataHora;

    @Enumerated(value = EnumType.STRING)
    private Plataforma origem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_afiliado_id")
    private LinkAfiliado linkAfiliado;
}
