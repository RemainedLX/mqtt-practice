package com.example.lx.mqtt.entity;

import lombok.Data;

/**
 * @author lx
 * @date 2025/4/19
 * @description
 */
@Data
public class PubLampMessage {
    private Integer lineStatus;
    private Integer lightStatus;
    private String deviceId;
}