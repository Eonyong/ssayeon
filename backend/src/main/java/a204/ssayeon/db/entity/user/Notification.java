package a204.ssayeon.db.entity.user;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Notification extends BaseEntity {

    private String description;
    private String url;
    private Boolean read;
}
