package com.example.lx.mqtt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lx
 * @date 2025/4/18
 * @description Mqtt配置
 */
@Data
@ConfigurationProperties(prefix = "spring.mqtt")
public class MqttConfigurationProperties {
    private String username;
    private String password;
    private String url;
    private String subClientId;
    private String subTopic;
    private String pubClientId;
}