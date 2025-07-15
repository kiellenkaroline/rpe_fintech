package rpe.challenge.fintech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpe.challenge.fintech.model.Fatura;
import rpe.challenge.fintech.service.FaturaService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/faturas")
public class FaturaController {

    @Autowired
    private FaturaService faturaService;

    @GetMapping
    public List<Fatura> listarTodas() {
        return faturaService.listarTodas();
    }

    @GetMapping("/atrasadas")
    public List<Fatura> listarAtrasadas() {
        return faturaService.listarAtrasadas();
    }

    @PutMapping("/{id}/pagamento")
    public ResponseEntity<String> pagarFatura(@PathVariable Long id) {
        try {
            faturaService.pagar(id);
            return ResponseEntity.ok("Fatura paga com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
