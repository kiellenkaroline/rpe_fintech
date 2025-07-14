package rpe.challenge.fintech.service;

import org.springframework.stereotype.Service;
import rpe.challenge.fintech.dtos.FaturaDTO;
import rpe.challenge.fintech.enums.StatusFatura;
import rpe.challenge.fintech.model.Cliente;
import rpe.challenge.fintech.model.Fatura;
import rpe.challenge.fintech.repository.FaturaRepository;

@Service
public class FaturaService {

    private FaturaRepository faturaRepository;
    private ClienteService clienteService;

    public Fatura criar(FaturaDTO dto){
        Cliente cliente = clienteService.buscar(dto.clienteId());


        Fatura fatura = new Fatura();
        fatura.setCliente(cliente);
        fatura.setValor(dto.valor());
        fatura.setDataVencimento(dto.dataVencimento());
        fatura.setStatus(String.valueOf(StatusFatura.B));

        return faturaRepository.save(fatura);
    }
}
