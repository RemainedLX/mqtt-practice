package com.example.lx;

import com.example.lx.mqtt.config.MqttConfigurationProperties;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jca.context.SpringContextResourceAdapter;

@SpringBootApplication
@MapperScan(basePackages = "com.example.lx.lamp.mapper")
@EnableConfigurationProperties(MqttConfigurationProperties.class)
public class LxApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context =
                SpringApplication.run(LxApplication.class, args);
//        String[] beanNames = context.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
    }

}
