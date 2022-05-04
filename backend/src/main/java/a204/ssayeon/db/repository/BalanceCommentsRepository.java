package a204.ssayeon.db.repository;

import a204.ssayeon.db.entity.balance.Balance;
import a204.ssayeon.db.entity.balance.BalanceComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalanceCommentsRepository extends JpaRepository<BalanceComments,Long> {
    List<BalanceComments> findByBalance(Balance balance);
}
