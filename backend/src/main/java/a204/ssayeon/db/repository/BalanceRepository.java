package a204.ssayeon.db.repository;

import a204.ssayeon.db.entity.balance.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance,Long> {
}
