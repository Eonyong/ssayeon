package a204.ssayeon.db.entity.user;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@DynamicInsert
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Message extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(nullable = false, name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="receiver_id")
    private User receiver;

    private Boolean isRead;
}
