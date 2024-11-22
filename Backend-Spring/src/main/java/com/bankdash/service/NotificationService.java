package com.bankdash.service;

import com.bankdash.dto.NotificationDTO;
import com.bankdash.entity.Notification;
import com.bankdash.mapper.NotificationMapper;
import com.bankdash.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(NotificationMapper.INSTANCE::toNotificationDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO getNotificationById(String id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        return NotificationMapper.INSTANCE.toNotificationDTO(notification);
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = NotificationMapper.INSTANCE.toNotificationEntity(notificationDTO);
        return NotificationMapper.INSTANCE.toNotificationDTO(notificationRepository.save(notification));
    }

    public void deleteNotification(String id) {
        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException("Notification not found");
        }
        notificationRepository.deleteById(id);
    }
}
