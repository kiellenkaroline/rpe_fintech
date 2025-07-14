package rpe.challenge.fintech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpe.challenge.fintech.dtos.ClienteDTO;
import rpe.challenge.fintech.model.Cliente;
import rpe.challenge.fintech.service.ClienteService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping
    public List<Cliente> listar(){
        return clienteService.listar();
    }

    @PostMapping
    public ResponseEntity<Cliente>criar(@RequestBody ClienteDTO dto){
        Cliente novo = clienteService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id){

        return ResponseEntity.ok(clienteService.buscar(id));
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody ClienteDTO dto){
        return clienteService.atualizar(id, dto);
    }

    @GetMapping("/bloqueados")
    public List<Cliente> listarBloqueados(){
        return clienteService.listarBloqueados();
    }

}
