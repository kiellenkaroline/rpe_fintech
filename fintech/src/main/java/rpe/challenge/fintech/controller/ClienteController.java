package rpe.challenge.fintech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpe.challenge.fintech.dtos.ClienteDTO;
import rpe.challenge.fintech.model.Cliente;
import rpe.challenge.fintech.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> listar(){
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<Cliente>criar(@RequestBody ClienteDTO dto){
        Cliente novo = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id){
        return ResponseEntity.ok(service.buscar(id));
    }

}
