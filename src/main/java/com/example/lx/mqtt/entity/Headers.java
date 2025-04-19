package com.example.lx.mqtt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author lx
 * @date 2025/4/19
 * @description headers={mqtt_receivedRetained=false, mqtt_id=0, mqtt_duplicate=false,
 * id=81b53bc2-84bd-bc45-b0d8-810f1013e7dd,
 * mqtt_receivedTopic=springboot/a, mqtt_receivedQos=0, timestamp=1745028918489}]
 */
@Data
public class Headers {
    /**
     *  是否保留消息
     */
    @JsonProperty("mqtt_receivedRetained")
    private Boolean mqttReceivedRetained;
    /**
     *
     */
    @JsonProperty("mqtt_id")
    private Integer mqttId;

    /**
     * 是否重复
     */
    @JsonProperty("mqtt_duplicate")
    private Boolean mqttDuplicate;
    /**
     * id
     */
    private String id;

    /**
     * 主题
     */
    @JsonProperty("mqtt_receivedTopic")
    private String mqttReceivedTopic;
    /**
     * qos
     */
    @JsonProperty("mqtt_receivedQos")
    private String mqttReceivedQos;
    /**
     * 时间戳
     */
    private String timestamp;
}