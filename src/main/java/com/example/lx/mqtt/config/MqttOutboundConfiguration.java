package com.example.lx.mqtt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @author lx
 * @date 2025/4/18
 * @description 出栈消息
 */
@Configuration
public class MqttOutboundConfiguration {

    @Autowired
    private MqttConfigurationProperties mqttConfigurationProperties;
    @Autowired
    private MqttPahoClientFactory mqttPahoClientFactory;



    // 消息通道
    @Bean("mqttOutboundChannel")
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
    // 出 消息处理器
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler messageOutboundHandler() {
        MqttPahoMessageHandler mqttPahoMessageHandler = new MqttPahoMessageHandler(
                mqttConfigurationProperties.getUrl(),
                mqttConfigurationProperties.getPubClientId(),
                mqttPahoClientFactory
        );
        mqttPahoMessageHandler.setDefaultQos(0);
        mqttPahoMessageHandler.setAsync(true);
        mqttPahoMessageHandler.setDefaultTopic("default");
        return mqttPahoMessageHandler;
    }
}