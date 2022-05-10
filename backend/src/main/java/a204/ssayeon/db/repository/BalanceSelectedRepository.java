package a204.ssayeon.db.repository;

import a204.ssayeon.db.entity.balance.Balance;
import a204.ssayeon.db.entity.balance.BalanceSelected;
import a204.ssayeon.db.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BalanceSelectedRepository extends JpaRepository<BalanceSelected,Long> {
    List<BalanceSelected> findByBalance(Balance balance);
    Optional<BalanceSelected> findByBalanceAndUser(Balance balance, User user);
}
