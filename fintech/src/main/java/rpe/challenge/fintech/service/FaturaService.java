package rpe.challenge.fintech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rpe.challenge.fintech.enums.StatusFatura;
import rpe.challenge.fintech.model.Fatura;
import rpe.challenge.fintech.repository.ClienteRepository;
import rpe.challenge.fintech.repository.FaturaRepository;

import java.util.List;

@Service
public class FaturaService {

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Fatura> listarTodas() {
        return faturaRepository.findAll();
    }

    public List<Fatura> listarAtrasadas() {
        return faturaRepository.findByStatus(StatusFatura.A);
    }

    public void pagar(Long id) {
        Fatura fatura = faturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fatura não encontrada"));

        if (StatusFatura.P.equals(fatura.getStatus())) {
            throw new IllegalArgumentException("Fatura já está paga.");
        }

        fatura.setStatus(StatusFatura.P);
        faturaRepository.save(fatura);
    }
}

