package a204.ssayeon.db.entity.preference;

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
public class PreferenceOptionsUserSelected {

    @Id @GeneratedValue
    @Column(name="preference_options_user_selected_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "preference_id")
    private Preference preference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "preference_options_id")
    private PreferenceOptions preferenceOptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
