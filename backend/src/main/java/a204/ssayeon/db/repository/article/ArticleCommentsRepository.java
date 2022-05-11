package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.ArticleComments;
import a204.ssayeon.db.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleCommentsRepository extends JpaRepository<ArticleComments, Long> {

    Optional<ArticleComments> findByIdAndUser(Long id, User user);

    List<ArticleComments> findByArticleId(Long id);
}
