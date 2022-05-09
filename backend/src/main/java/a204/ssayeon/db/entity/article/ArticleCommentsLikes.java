package a204.ssayeon.db.entity.article;

import a204.ssayeon.db.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ArticleCommentsLikes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_comments_likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_comments_id",nullable = false)
    private ArticleComments articleComments;
}
