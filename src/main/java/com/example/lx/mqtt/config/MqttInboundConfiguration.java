package com.example.lx.mqtt.config;

import com.example.lx.mqtt.handler.ReceiveMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @author lx
 * @date 2025/4/18
 * @description 入栈消息
 */
@Configuration
public class MqttInboundConfiguration {
    @Autowired
    private MqttConfigurationProperties mqttConfigurationProperties;
    @Autowired
    private MqttPahoClientFactory mqttPahoClientFactory;
    @Autowired
    private ReceiveMessageHandler receiveMessageHandler;
    // 消息通道
    @Bean
    public MessageChannel messageInboundChannel() {
        return new DirectChannel();
    }
    // 入栈适配器  设置订阅的主题 以及指定消息的相关属性
    @Bean
    public MessageProducer messageProducer() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                mqttConfigurationProperties.getUrl(),
                mqttConfigurationProperties.getSubClientId(),
                mqttPahoClientFactory,
                mqttConfigurationProperties.getSubTopic().split(","));

        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setOutputChannel(messageInboundChannel());
        adapter.setQos(1);
        return adapter;
    }
    // 消息处理器
    @Bean
    @ServiceActivator(inputChannel = "messageInboundChannel")  // 获取消息时的通道
    public MessageHandler messageInboundHandler() {
        return receiveMessageHandler;
    }
}