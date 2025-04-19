package com.example.lx.lamp.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.lx.lamp.entity.Lamp;
import com.example.lx.lamp.entity.LampStatus;
import com.example.lx.lamp.service.LampService;
import com.example.lx.lamp.service.LampStatusService;
import com.example.lx.mqtt.entity.PubLampMessage;
import com.example.lx.mqtt.service.MqttMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lx
 * @date 2025/4/19
 * @description
 */
@RequestMapping("lamp")
@RestController
public class LampController {

    @Autowired
    private MqttMessageSender mqttMessageSender;
    @Autowired
    private LampService lampService;
    @Autowired
    private LampStatusService lampStatusService;
    @Value("${spring.mqtt.pubTopic}")
    private String pubTopic;

    /**
     * 获取开灯的状态
     * @param deviceId deviceId
     */
    @GetMapping("/light/{deviceId}")
    public void lightStatus(@PathVariable("deviceId") String deviceId) {
        LampStatus one = lampStatusService.getOne(new LambdaQueryWrapper<LampStatus>()
                .eq(LampStatus::getDeviceId, deviceId));
        if (one != null) {
            mqttMessageSender.sendMessage(pubTopic, one.getStatus().toString());
        }
    }
    /**
     * 获取在线状态
     * @param deviceId deviceId
     */
    @GetMapping("/line/{deviceId}")
    public void lineStatus(@PathVariable("deviceId") String deviceId) {
        Lamp one = lampService.getOne(new LambdaQueryWrapper<Lamp>()
                .eq(Lamp::getDeviceId, deviceId));
        if (one != null) {
            mqttMessageSender.sendMessage(pubTopic, one.getStatus().toString());
        }
    }

    /**
     * 获取所有状态
     * @param deviceId deviceId
     */
    @GetMapping("/all/{deviceId}")
    public String allStatus(@PathVariable("deviceId") String deviceId) {
        PubLampMessage pubLampMessage = new PubLampMessage();

        LampStatus light = lampStatusService.getOne(new LambdaQueryWrapper<LampStatus>()
                .eq(LampStatus::getDeviceId, deviceId));
        if (light != null) {
            pubLampMessage.setLightStatus(light.getStatus());
        }
        Lamp line = lampService.getOne(new LambdaQueryWrapper<Lamp>()
                .eq(Lamp::getDeviceId, deviceId));

        if (line != null) {
            pubLampMessage.setLineStatus(line.getStatus());
        }
        pubLampMessage.setDeviceId(deviceId);

        // 转成JSON
        String res = JSON.toJSONString(pubLampMessage);
        mqttMessageSender.sendMessage(pubTopic, res);
        return res;
    }


}