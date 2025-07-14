package rpe.challenge.fintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rpe.challenge.fintech.model.Fatura;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {
}

