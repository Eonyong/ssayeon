package a204.ssayeon.db.repository.user;

import a204.ssayeon.db.entity.user.UserCertification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCertificationRepository extends JpaRepository<UserCertification, Long> {
    boolean existsByNameAndClassId(String name, String classId);
}
