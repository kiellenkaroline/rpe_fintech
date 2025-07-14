package rpe.challenge.fintech.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FaturaDTO(Long clienteId, BigDecimal valor, LocalDate dataVencimento) {
}
