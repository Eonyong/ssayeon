package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.Article;
import a204.ssayeon.db.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByBoardId(Long boardId);

    Optional<Article> findByIdAndUser(Long Id, User user);

}
