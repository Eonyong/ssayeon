package a204.ssayeon.db.repository.user;

import a204.ssayeon.db.entity.user.Alarm;
import a204.ssayeon.db.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    Page<Alarm> findByUser(User user, Pageable pageable);

    @Modifying
    @Query("UPDATE Alarm a SET a.isRead=true WHERE a.id=:id")
    void updateRead(Long id);
}

