package rpe.challenge.fintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rpe.challenge.fintech.enums.StatusFatura;
import rpe.challenge.fintech.model.Fatura;

import java.util.List;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {
    List<Fatura> findByStatus(StatusFatura status);
}


