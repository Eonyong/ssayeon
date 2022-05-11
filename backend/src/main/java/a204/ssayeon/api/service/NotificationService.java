package a204.ssayeon.api.service;

import a204.ssayeon.api.request.notification.NotificationCreateReq;
import a204.ssayeon.api.request.notification.NotificationUpdateReq;
import a204.ssayeon.common.exceptions.NotExistException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.db.entity.notification.Notification;
import a204.ssayeon.db.repository.Notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    @Transactional
    public void create(NotificationCreateReq notificationCreateReq) {
        notificationRepository.save(notificationCreateReq.toNotification());
    }

    @Transactional
    public void update(Long id, NotificationUpdateReq notificationUpdateReq) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
        notification.setTitle(notificationUpdateReq.getTitle());
        notification.setDescription(notificationUpdateReq.getDescription());

        notificationRepository.save(notification);
    }

    @Transactional
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Notification> showList(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    @Transactional
    public Notification showDetail(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
        notification.updateViews();
        return notificationRepository.save(notification);
    }


}
