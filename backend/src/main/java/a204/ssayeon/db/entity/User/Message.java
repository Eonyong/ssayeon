package a204.ssayeon.db.entity.User;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Message extends BaseEntity {

    String description;

    @ManyToOne
    @JoinColumn(name="senderId")
    User sender;

    @ManyToOne
    @JoinColumn(name="receiverId")
    User receiver;
}
