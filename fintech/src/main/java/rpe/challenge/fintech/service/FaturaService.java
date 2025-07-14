package rpe.challenge.fintech.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rpe.challenge.fintech.dtos.FaturaDTO;
import rpe.challenge.fintech.enums.StatusBloqueio;
import rpe.challenge.fintech.enums.StatusFatura;
import rpe.challenge.fintech.model.Cliente;
import rpe.challenge.fintech.model.Fatura;
import rpe.challenge.fintech.repository.FaturaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FaturaService {

    private final FaturaRepository faturaRepository;
    private final ClienteService clienteService;

    @Transactional
    public Fatura criar(FaturaDTO dto){
        Cliente cliente = clienteService.buscar(dto.clienteId());


        Fatura fatura = new Fatura();
        fatura.setCliente(cliente);
        fatura.setValor(dto.valor());
        fatura.setDataVencimento(dto.dataVencimento());
        fatura.setStatus(StatusFatura.B);

        return faturaRepository.save(fatura);
    }

    public List<Fatura> listar(){
        return faturaRepository.findAll();
    }
    public List<Fatura> listarPorCliente(Long clienteId) {
        Cliente cliente = clienteService.buscar(clienteId);
        return cliente.getFaturas();
    }

    public List<Fatura> listarAtrasadas() {
        return faturaRepository.findByStatus(StatusFatura.A);
    }

    @Transactional
    public void atualizarStatusFaturas(){
        List<Fatura> abertas = faturaRepository.findByStatus(StatusFatura.B);
        LocalDate hoje = LocalDate.now();

        for(Fatura fatura : abertas){
            if(fatura.getDataVencimento().isBefore(hoje)){
                fatura.setStatus(StatusFatura.A);
                fatura.getCliente().setStatusBloqueio(StatusBloqueio.B);
            }
        }

        faturaRepository.saveAll(abertas);
    }

    @Transactional
    public Fatura pagar(Long faturaId){
        Fatura fatura = faturaRepository.findById(faturaId)
                .orElseThrow(() -> new EntityNotFoundException("Fatura não encontrada com ID: " + faturaId));

        if(fatura.getStatus() == StatusFatura.P){
            throw new IllegalStateException("Fatura já está paga.");

        }


        fatura.setStatus(StatusFatura.P);

        Cliente cliente = fatura.getCliente();

        boolean possuiFaturaAtrasada = cliente.getFaturas().stream()
                .anyMatch(f -> f.getStatus() == StatusFatura.A);

        if (!possuiFaturaAtrasada) {
            cliente.setStatusBloqueio(StatusBloqueio.A);
        }

        return faturaRepository.save(fatura);
    }
}
