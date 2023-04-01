package NotificationsSenderService.services;

import NotificationsSenderService.entities.Notification;
import NotificationsSenderService.repositories.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public void addNotification(String header, String text, Long userId) {
        Long id = (long) (notificationRepository.findAll().size() + 1);
        final Notification notification = new Notification(id, header, text, new Date(), userId);
        notificationRepository.save(notification);
    }

    @Transactional
    public void updateNotification(Long id, String header, String text, Date date, Long userId) {
        final Notification notification = findNotification(id);
        notification.setHeader(header);
        notification.setText(text);
        notification.setDate(date);
        notification.setUserId(userId);
        notificationRepository.save(notification);

    }

    @Transactional
    public void deleteNotification(Long id) {
        final Notification notification = findNotification(id);
        notificationRepository.delete(notification);
    }

    @Transactional(readOnly = true)
    public Notification findNotification(Long id) {
        final Optional<Notification> notification = notificationRepository.findById(id);
        return notification.orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Notification> findAllNotifications() {
        return notificationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Notification> findNotificationsByUserId(Long id) {
        return notificationRepository.findAllByUserId(id);
    }
}
