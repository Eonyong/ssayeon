package a204.ssayeon.db.entity.preference;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PreferenceComment extends BaseEntity {

    private String description;
}
