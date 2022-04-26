package a204.ssayeon.db.entity.article;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CategoryHasBoard extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="category_has_board")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id",nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id",nullable = false)
    private Board board;
}
