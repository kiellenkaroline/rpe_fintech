package rpe.challenge.fintech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpe.challenge.fintech.dtos.FaturaDTO;
import rpe.challenge.fintech.dtos.PagamentoDTO;
import rpe.challenge.fintech.model.Fatura;
import rpe.challenge.fintech.service.FaturaService;

import java.util.List;

public class FaturaController {
    private FaturaService service;

    @GetMapping
    public List<Fatura>listar (){

        return service.listar();
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Fatura> listraPorCliente(@PathVariable Long clienteId){
        return service.listarPorCliente(clienteId);
    }

    public List<Fatura> listarAtrasadas(){
        return service.listarAtrasadas();
    }
    @PostMapping
    public ResponseEntity<Fatura> criar(@RequestBody FaturaDTO dto){
        Fatura nova = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @PutMapping
    public ResponseEntity<Void> atualizarStatus(){
        service.atualizarStatusFaturas();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Fatura> pagar(@RequestBody PagamentoDTO dto){
        Fatura paga = service.pagar(dto.faturaId());
        return ResponseEntity.ok(paga);
    }
}
