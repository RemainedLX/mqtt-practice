package com.example.lx.mqtt.service;

import com.example.lx.mqtt.gateway.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lx
 * @date 2025/4/18
 * @description
 */
@Component
public class MqttMessageSender  {
    @Autowired
    MqttGateway mqttGateway;
    public void sendMessage(String topic, String message) {
        mqttGateway.sendToMqtt(topic,message);
        System.out.println("发送消息："+topic);
    }
    public void sendMessage(String topic,String qos, String message) {
        mqttGateway.sendToMqtt(topic,qos,message);
    }
}