package a204.ssayeon.db.entity.Article;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BalanceSelected extends BaseEntity {

    boolean selected;
}
