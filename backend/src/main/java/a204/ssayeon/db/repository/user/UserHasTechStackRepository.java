package a204.ssayeon.db.repository.user;

import a204.ssayeon.db.entity.user.TechStack;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.entity.user.UserHasTechStack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserHasTechStackRepository extends JpaRepository<UserHasTechStack, Long> {
    Optional<UserHasTechStack> findByUserAndTechStack(User user, TechStack techStack);
    List<UserHasTechStack> findByUser(User user);

    void deleteIn(List<UserHasTechStack> userHasTechStacks);
}
