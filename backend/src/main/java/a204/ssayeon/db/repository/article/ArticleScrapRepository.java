package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.ArticleScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleScrapRepository extends JpaRepository<ArticleScrap, Long> {

    ArticleScrap findByArticleIdAndUserId(Long articleId, Long userId);
}
