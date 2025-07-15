package rpe.challenge.fintech.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rpe.challenge.fintech.enums.StatusBloqueio;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private StatusBloqueio statusBloqueio;
    private BigDecimal limiteCredito;
}
