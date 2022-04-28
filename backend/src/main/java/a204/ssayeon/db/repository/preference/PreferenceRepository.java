package a204.ssayeon.db.repository.preference;


import a204.ssayeon.db.entity.preference.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

}