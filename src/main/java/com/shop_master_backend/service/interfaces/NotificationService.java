package com.shop_master_backend.service.interfaces;

import com.shop_master_backend.event.EntityUpdateEvent;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {

    public SseEmitter subscribe(String topic);

    public void handleEntityUpdate(EntityUpdateEvent<?> event);

}
