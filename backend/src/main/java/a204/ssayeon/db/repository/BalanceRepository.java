package a204.ssayeon.db.repository;

import a204.ssayeon.db.entity.balance.Balance;
import a204.ssayeon.db.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance,Long> {
    Optional<Balance> findByIdAndUser(Long balanceId, User user);
}
