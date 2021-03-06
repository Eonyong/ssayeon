package a204.ssayeon.db.entity.preference;

import a204.ssayeon.db.entity.BaseEntity;
import a204.ssayeon.db.entity.user.User;
import lombok.*;

import javax.persistence.*;


@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Preference extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="preference_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(nullable = false)
    private String description;

    public void setDescription(String description){
        this.description = description;
    }
}

