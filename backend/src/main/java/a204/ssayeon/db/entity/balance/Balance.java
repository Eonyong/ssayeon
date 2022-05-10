package a204.ssayeon.db.entity.balance;

import a204.ssayeon.db.entity.BaseEntity;
import a204.ssayeon.db.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Balance extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="balance_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String leftDescription;

    @Column(nullable = false)
    private String rightDescription;

    public void updateDescription(String description,String leftDescription,String rightDescription) {
        this.description = description;
        this.leftDescription = leftDescription;
        this.rightDescription = rightDescription;
    }
}
