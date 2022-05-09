package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
