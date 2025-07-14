package rpe.challenge.fintech.model;

import jakarta.persistence.*;
import lombok.Data;
import rpe.challenge.fintech.enums.StatusFatura;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "fatura")
@Data
public class Fatura {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusFatura status;
}