package com.bankdash.mapper;

import com.bankdash.dto.NotificationDTO;
import com.bankdash.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);
    NotificationDTO toNotificationDTO(Notification notification);
    Notification toNotificationEntity(NotificationDTO notificationDTO);
}
