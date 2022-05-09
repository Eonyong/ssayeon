package a204.ssayeon.db.entity.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ArticleHasTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_has_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id",nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id",nullable = false)
    private Tag tag;
}
