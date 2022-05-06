package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.ArticleAnswer;
import a204.ssayeon.db.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleAnswerRepository extends JpaRepository<ArticleAnswer, Long> {

    List<ArticleAnswer> findByArticleId(Long articleId);

    Optional<ArticleAnswer> findByIdAndUser(Long articleAnswerId, User user);
}
