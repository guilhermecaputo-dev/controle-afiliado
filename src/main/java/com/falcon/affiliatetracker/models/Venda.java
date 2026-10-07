package com.falcon.affiliatetracker.models;

import com.falcon.affiliatetracker.models.enums.Plataforma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vendas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate dataVenda;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorProduto;

    @Column(precision = 5, scale = 2)
    private BigDecimal percentualComissao;

    @Column(precision = 6, scale = 2)
    private BigDecimal valorComissao;

    @Enumerated(EnumType.STRING)
    private Plataforma origem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;


}
