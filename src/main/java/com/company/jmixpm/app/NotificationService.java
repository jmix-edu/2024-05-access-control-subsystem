package com.company.jmixpm.app;

import com.company.jmixpm.entity.Notification;
import io.jmix.core.UnconstrainedDataManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NotificationService {

    private UnconstrainedDataManager unconstrainedDataManager;

    @PersistenceContext
    private EntityManager entityManager;

    public NotificationService(UnconstrainedDataManager unconstrainedDataManager) {
        this.unconstrainedDataManager = unconstrainedDataManager;
    }

    public void markAsRead(Notification notification) {
        notification = unconstrainedDataManager.load(Notification.class)
                .id(notification.getId())
                .one();

        notification.setIsRead(true);

        unconstrainedDataManager.save(notification);
    }

    @Transactional
    public void markAsReadEM(Notification notification) {
        notification = entityManager.find(Notification.class, notification.getId());
        notification.setIsRead(true);
    }
}