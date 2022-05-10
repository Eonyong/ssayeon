package a204.ssayeon.db.repository.Notification;

import a204.ssayeon.db.entity.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

