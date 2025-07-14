package rpe.challenge.fintech.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "cliente")
@Data
public class Cliente {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    @Column(name = "status_bloqueio")
    private String statusBloqueio;

    @Column(name = "limite_credito")
    private BigDecimal limiteCredito;
}
