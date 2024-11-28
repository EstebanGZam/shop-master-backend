package com.shop_master_backend.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EntityUpdateEvent<T> {
    private final String topic;
    private final T data;
}
