package a204.ssayeon.db.entity.article;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ArticleComment extends BaseEntity {
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
