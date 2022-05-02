package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.ArticleHasTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleHasTagRepository extends JpaRepository<ArticleHasTag, Long> {

    List<ArticleHasTag> findByArticleId(Long articleId);

}
