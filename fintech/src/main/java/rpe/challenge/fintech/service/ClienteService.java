package rpe.challenge.fintech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rpe.challenge.fintech.enums.StatusBloqueio;
import rpe.challenge.fintech.model.Cliente;
import rpe.challenge.fintech.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        cliente.setStatusBloqueio(StatusBloqueio.B); // padrão
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setCpf(clienteAtualizado.getCpf());
        cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
        cliente.setLimiteCredito(clienteAtualizado.getLimiteCredito());
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarBloqueados() {
        return clienteRepository.findByStatusBloqueio(StatusBloqueio.B);
    }
}
