package a204.ssayeon.db.repository;

import a204.ssayeon.db.entity.balance.BalanceComments;
import a204.ssayeon.db.entity.balance.BalanceCommentsLikes;
import a204.ssayeon.db.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BalanceCommentsLikesRepository extends JpaRepository<BalanceCommentsLikes,Long> {
    List<BalanceCommentsLikes> findByBalanceComments(BalanceComments balanceComments);

    Optional<BalanceCommentsLikes> findByIdAndUser(Long commentsId, User user);
}
