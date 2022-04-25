package a204.ssayeon.db.entity.User;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class TechStack extends BaseEntity {

    String description;

}
