package rpe.challenge.fintech.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rpe.challenge.fintech.enums.StatusFatura;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaturaDTO {
    private Long id;
    private Long clienteId;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private StatusFatura status;
}
