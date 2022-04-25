package a204.ssayeon.db.entity.Article;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Article extends BaseEntity {

    String title;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    int views;

}
