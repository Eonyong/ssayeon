package a204.ssayeon.db.entity.user;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Message extends BaseEntity {

    private String description;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name="senderId")
    private User sender;
    @ManyToOne
    @JoinColumn(name="receiverId")
    private User receiver;
}
