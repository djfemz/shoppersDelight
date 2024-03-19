package africa.semicolon.shoppersDelight.repositories;

import africa.semicolon.shoppersDelight.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

}
