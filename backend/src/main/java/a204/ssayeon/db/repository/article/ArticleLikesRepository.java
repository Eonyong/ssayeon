package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.ArticleLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikesRepository extends JpaRepository<ArticleLikes, Long> {

    ArticleLikes findByArticleIdAndUserId(Long articleId, Long userId);
}
