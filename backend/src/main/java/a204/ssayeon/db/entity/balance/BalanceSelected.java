package a204.ssayeon.db.entity.balance;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BalanceSelected extends BaseEntity {

    private Boolean selected;

}
