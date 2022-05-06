package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
