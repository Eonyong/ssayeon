package a204.ssayeon.db.repository;

import a204.ssayeon.db.entity.balance.Balance;
import a204.ssayeon.db.entity.balance.BalanceSelected;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalanceSelectedRepository extends JpaRepository<BalanceSelected,Long> {
    List<BalanceSelected> findByBalance(Balance balance);
}
