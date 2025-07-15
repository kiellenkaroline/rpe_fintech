package rpe.challenge.fintech.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rpe.challenge.fintech.enums.StatusBloqueio;
import rpe.challenge.fintech.model.Cliente;
import rpe.challenge.fintech.repository.ClienteRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setCpf("123.456.789-00");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
        cliente.setLimiteCredito(new BigDecimal("1000.00"));
        cliente.setStatusBloqueio(StatusBloqueio.B);
    }

    @Test
    void deveListarTodosClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> clientes = clienteService.listarTodos();

        assertThat(clientes).hasSize(1);
        assertThat(clientes.get(0).getNome()).isEqualTo("João");
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void deveSalvarClienteComStatusBloqueado() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente salvo = clienteService.salvar(cliente);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getStatusBloqueio()).isEqualTo(StatusBloqueio.B);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void deveBuscarClientePorId() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> encontrado = clienteService.buscarPorId(1L);

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getCpf()).isEqualTo("123.456.789-00");
        verify(clienteRepository).findById(1L);
    }

    @Test
    void deveAtualizarCliente() {
        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setNome("Maria");
        clienteAtualizado.setCpf("987.654.321-00");
        clienteAtualizado.setDataNascimento(LocalDate.of(1985, 5, 10));
        clienteAtualizado.setLimiteCredito(new BigDecimal("2000.00"));

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.atualizar(1L, clienteAtualizado);

        assertThat(resultado.getNome()).isEqualTo("Maria");
        assertThat(resultado.getCpf()).isEqualTo("987.654.321-00");
        verify(clienteRepository).findById(1L);
        verify(clienteRepository).save(cliente);
    }

    @Test
    void deveListarClientesBloqueados() {
        when(clienteRepository.findByStatusBloqueio(StatusBloqueio.B)).thenReturn(List.of(cliente));

        List<Cliente> bloqueados = clienteService.listarBloqueados();

        assertThat(bloqueados).hasSize(1);
        assertThat(bloqueados.get(0).getStatusBloqueio()).isEqualTo(StatusBloqueio.B);
        verify(clienteRepository).findByStatusBloqueio(StatusBloqueio.B);
    }
}

