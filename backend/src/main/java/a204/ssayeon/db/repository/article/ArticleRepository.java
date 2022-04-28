package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
