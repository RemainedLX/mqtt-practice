package com.example.lx.mqtt.config;

import com.example.lx.mqtt.config.MqttConfigurationProperties;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

/**
 * @author lx
 * @date 2025/4/18
 * @description  Mqtt 连接工厂
 */
@Configuration
public class MqttConfiguration {
    @Autowired
    private MqttConfigurationProperties mqttConfigurationProperties;

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory defaultMqttPahoClientFactory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        // 创建一个新的连接
        options.setCleanSession(true);
        options.setUserName(mqttConfigurationProperties.getUsername());
        options.setPassword(mqttConfigurationProperties.getPassword().toCharArray());
        options.setServerURIs(new String[]{mqttConfigurationProperties.getUrl()});
        // 设置连接选项
        defaultMqttPahoClientFactory.setConnectionOptions(options);
        return defaultMqttPahoClientFactory;
    }
}