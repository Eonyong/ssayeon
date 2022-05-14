package a204.ssayeon.db.repository.article;

import a204.ssayeon.db.entity.article.Article;
import a204.ssayeon.db.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByBoardId(Long boardId, Pageable pageable);

    Optional<Article> findByIdAndUser(Long Id, User user);

    List<Article> findByTitleContainsAndBoardId(String title, Long boardId);

    List<Article> findByContentContainsAndBoardId(String content, Long boardId);

    List<Article> findByUserIdAndBoardId(Long userId, Long boardId);

    List<Article> findTop10ByBoardIdOrderByLikesCountDesc(Long boardId);
    
    Optional<Article> findByUserId(Long userId);
    Page<Article> findByUserId(Long userId, Pageable pageable);

    List<Article> findTop3ByBoardIdOrderByIdDesc(Long boardId);

}
