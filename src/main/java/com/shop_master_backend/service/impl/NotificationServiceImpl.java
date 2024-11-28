package com.shop_master_backend.service.impl;

import com.shop_master_backend.event.EntityUpdateEvent;
import com.shop_master_backend.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final Map<String, List<SseEmitter>> emittersMap = new ConcurrentHashMap<>();

    @Override
    public SseEmitter subscribe(String topic) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        List<SseEmitter> topicEmitters = emittersMap.computeIfAbsent(topic, k -> new CopyOnWriteArrayList<>());
        topicEmitters.add(emitter);

        emitter.onCompletion(() -> removeEmitter(topic, emitter));
        emitter.onTimeout(() -> removeEmitter(topic, emitter));

        return emitter;
    }

    @EventListener
    @Override
    public void handleEntityUpdate(EntityUpdateEvent<?> event) {
        notify(event.getTopic(), event.getData());
    }

    private <T> void notify(String topic, T data) {
        List<SseEmitter> topicEmitters = emittersMap.get(topic);
        if (topicEmitters != null) {
            List<SseEmitter> deadEmitters = new ArrayList<>();

            topicEmitters.forEach(emitter -> {
                try {
                    emitter.send(SseEmitter.event()
                            .name(topic)
                            .data(data));
                } catch (IOException e) {
                    deadEmitters.add(emitter);
                }
            });

            topicEmitters.removeAll(deadEmitters);
        }
    }

    private void removeEmitter(String topic, SseEmitter emitter) {
        List<SseEmitter> topicEmitters = emittersMap.get(topic);
        if (topicEmitters != null) {
            topicEmitters.remove(emitter);
            if (topicEmitters.isEmpty()) {
                emittersMap.remove(topic);
            }
        }
    }
}