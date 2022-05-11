package a204.ssayeon.db.entity.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@DynamicInsert
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private Long id;

    private String name;
    private Integer count;
}
