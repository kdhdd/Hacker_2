package Hackerton.CRUD.Repository;

import Hackerton.CRUD.domain.CoinTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinTransactionRepository extends JpaRepository<CoinTransaction, Long> {
}