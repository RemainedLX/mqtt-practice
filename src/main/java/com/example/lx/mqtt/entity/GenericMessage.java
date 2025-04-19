package com.example.lx.mqtt.entity;

import lombok.Data;

/**
 * @author lx
 * @date 2025/4/19
 * @description
 */
@Data
public class GenericMessage {
    private LampPayload lampPayload;
    private Headers headers;
}