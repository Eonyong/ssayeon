package a204.ssayeon.db.repository;

import a204.ssayeon.db.entity.balance.BalanceComments;
import a204.ssayeon.db.entity.balance.BalanceCommentsLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalanceCommentsLikesRepository extends JpaRepository<BalanceCommentsLikes,Long> {
    List<BalanceCommentsLikes> findByBalanceComments(BalanceComments balanceComments);
}
