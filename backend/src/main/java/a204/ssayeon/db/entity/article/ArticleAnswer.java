package a204.ssayeon.db.entity.article;

import a204.ssayeon.db.entity.BaseEntity;
import a204.ssayeon.db.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ArticleAnswer extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="article_answer_id")
    private Long id;

    @Column(nullable = false)
    private String description;
    private Boolean isSelected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id",nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;
}
