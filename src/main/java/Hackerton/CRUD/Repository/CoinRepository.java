package Hackerton.CRUD.Repository;
import Hackerton.CRUD.domain.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
    Coin findByMemberId(Long memberId);
}