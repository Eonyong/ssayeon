package a204.ssayeon.db.entity.balance;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Balance extends BaseEntity {

    private String description;
    private String leftDescription;
    private String rightDescription;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
