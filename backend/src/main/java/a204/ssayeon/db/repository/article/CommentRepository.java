package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.ArticleComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<ArticleComments, Long> {
}
