package a204.ssayeon.db.entity.preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PreferenceOptions{

    @Id @GeneratedValue
    @Column(name="preference_options_id")
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "preference_id")
    private Preference preference;

    @Column(nullable = false)
    private String description;
}
