package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByBoardId(Long boardId);

}
