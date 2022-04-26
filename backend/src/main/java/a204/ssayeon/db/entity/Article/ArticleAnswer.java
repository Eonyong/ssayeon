package a204.ssayeon.db.entity.article;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class ArticleAnswer extends BaseEntity {

    private String description;
    private Boolean isSelected;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
