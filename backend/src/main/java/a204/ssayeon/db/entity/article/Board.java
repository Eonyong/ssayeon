package a204.ssayeon.db.entity.article;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="board_id")
    private Long id;

    private String name;
}
