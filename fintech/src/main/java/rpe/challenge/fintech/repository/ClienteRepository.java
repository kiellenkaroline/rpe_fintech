package rpe.challenge.fintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rpe.challenge.fintech.model.Cliente;
import rpe.challenge.fintech.enums.StatusBloqueio;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByStatusBloqueio(StatusBloqueio statusBloqueio);
}
