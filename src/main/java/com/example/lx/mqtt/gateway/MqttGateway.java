package com.example.lx.mqtt.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author lx
 * @date 2025/4/18
 * @description mqtt网关
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    public abstract void sendToMqtt(@Header(value = MqttHeaders.TOPIC) String topic, String payload);
    public abstract void sendToMqtt(@Header(value = MqttHeaders.TOPIC) String topic,
                                    @Header(value = MqttHeaders.QOS) String qos,
                                    String payload);
}