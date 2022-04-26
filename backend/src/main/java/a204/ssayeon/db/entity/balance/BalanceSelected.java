package a204.ssayeon.db.entity.balance;

import a204.ssayeon.db.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BalanceSelected {

    @Id @GeneratedValue
    @Column(name="balance_selected_id")
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "balance_id")
    private Balance balance;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(nullable = false)
    private Boolean selected;
}
