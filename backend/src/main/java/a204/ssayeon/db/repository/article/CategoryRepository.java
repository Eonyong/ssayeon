package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
