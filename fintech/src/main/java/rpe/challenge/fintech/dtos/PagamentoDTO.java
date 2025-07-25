package rpe.challenge.fintech.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {
    private Long faturaId;
    private BigDecimal valorPago;
}

