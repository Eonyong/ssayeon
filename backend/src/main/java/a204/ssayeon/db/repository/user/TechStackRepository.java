package a204.ssayeon.db.repository.user;

import a204.ssayeon.db.entity.user.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechStackRepository extends JpaRepository<TechStack, Long> {
    Optional<TechStack> findByDescription(String description);
}
