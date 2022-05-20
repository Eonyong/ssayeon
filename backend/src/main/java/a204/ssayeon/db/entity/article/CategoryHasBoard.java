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
public class CategoryHasBoard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_has_board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id",nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id",nullable = false)
    private Board board;
}
