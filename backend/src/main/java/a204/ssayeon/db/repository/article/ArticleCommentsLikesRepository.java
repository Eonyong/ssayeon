package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.ArticleCommentsLikes;
import a204.ssayeon.db.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentsLikesRepository extends JpaRepository<ArticleCommentsLikes, Long> {

    ArticleCommentsLikes findByArticleCommentsIdAndUser(Long articleCommentsId, User user);
}
