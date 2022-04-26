package a204.ssayeon.db.entity.user;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class User extends BaseEntity {

    private String nickname;
    private String email;
    private String password;
    private String company;
    private String picture;

}
