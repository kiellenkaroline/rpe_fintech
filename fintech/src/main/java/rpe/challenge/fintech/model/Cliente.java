package rpe.challenge.fintech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rpe.challenge.fintech.enums.StatusBloqueio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Fatura> faturas;

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    @Column(name = "status_bloqueio")
    @Enumerated(EnumType.STRING)
    private StatusBloqueio statusBloqueio;

    @Column(name = "limite_credito")
    private BigDecimal limiteCredito;
}
