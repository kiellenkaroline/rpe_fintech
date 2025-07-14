package rpe.challenge.fintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rpe.challenge.fintech.enums.StatusBloqueio;
import rpe.challenge.fintech.model.Cliente;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByStatusBloqueio(StatusBloqueio statusBloqueio);
}
