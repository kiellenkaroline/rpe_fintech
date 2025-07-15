package rpe.challenge.fintech.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import rpe.challenge.fintech.enums.StatusFatura;
import rpe.challenge.fintech.model.Fatura;
import rpe.challenge.fintech.repository.ClienteRepository;
import rpe.challenge.fintech.repository.FaturaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class FaturaServiceTest {

    @Mock
    private FaturaRepository faturaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private FaturaService faturaService;

    private Fatura fatura;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        fatura = new Fatura();
        fatura.setId(1L);
        fatura.setValor(new BigDecimal("200.00"));
        fatura.setDataVencimento(LocalDate.now().minusDays(5));
        fatura.setStatus(StatusFatura.A);
    }

    @Test
    void deveListarTodasAsFaturas() {
        when(faturaRepository.findAll()).thenReturn(List.of(fatura));

        List<Fatura> faturas = faturaService.listarTodas();

        assertThat(faturas).hasSize(1);
        assertThat(faturas.get(0).getValor()).isEqualTo(new BigDecimal("200.00"));
        verify(faturaRepository).findAll();
    }

    @Test
    void deveListarFaturasAtrasadas() {
        when(faturaRepository.findByStatus(StatusFatura.A)).thenReturn(List.of(fatura));

        List<Fatura> atrasadas = faturaService.listarAtrasadas();

        assertThat(atrasadas).hasSize(1);
        assertThat(atrasadas.get(0).getStatus()).isEqualTo(StatusFatura.A);
        verify(faturaRepository).findByStatus(StatusFatura.A);
    }

    @Test
    void devePagarFatura() {
        when(faturaRepository.findById(1L)).thenReturn(Optional.of(fatura));
        when(faturaRepository.save(any(Fatura.class))).thenReturn(fatura);

        faturaService.pagar(1L);

        assertThat(fatura.getStatus()).isEqualTo(StatusFatura.P);
        verify(faturaRepository).save(fatura);
    }

    @Test
    void deveLancarErroQuandoFaturaNaoExiste() {
        when(faturaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> faturaService.pagar(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Fatura não encontrada");

        verify(faturaRepository, never()).save(any());
    }

    @Test
    void deveLancarErroQuandoFaturaJaEstiverPaga() {
        fatura.setStatus(StatusFatura.P);
        when(faturaRepository.findById(1L)).thenReturn(Optional.of(fatura));

        assertThatThrownBy(() -> faturaService.pagar(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Fatura já está paga.");

        verify(faturaRepository, never()).save(any());
    }
}
