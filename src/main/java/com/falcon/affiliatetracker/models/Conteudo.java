package com.falcon.affiliatetracker.models;

import com.falcon.affiliatetracker.models.enums.Plataforma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "conteudos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Plataforma plataforma;

    private String url;
    private LocalDate dataPublicacao;
    private Long visualizacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
