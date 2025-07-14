package rpe.challenge.fintech.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import rpe.challenge.fintech.dtos.ClienteDTO;
import rpe.challenge.fintech.enums.StatusBloqueio;
import rpe.challenge.fintech.model.Cliente;
import rpe.challenge.fintech.repository.ClienteRepository;
import java.util.List;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public Cliente criar(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    public Cliente buscar(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
    }

    public Cliente atualizar(Long id, ClienteDTO dto){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarBloqueados() {
        return clienteRepository.findByStatusBloqueio(StatusBloqueio.B);

    }
}
