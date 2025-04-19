package com.example.lx.mqtt.handler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.lx.lamp.service.LampService;
import com.example.lx.lamp.service.LampStatusService;
import com.example.lx.mqtt.entity.Headers;
import com.example.lx.mqtt.entity.LampPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * @author lx
 * @date 2025/4/18
 * @description 消息处理器
 */
@Component
public class ReceiveMessageHandler implements MessageHandler {
    @Value("${spring.mqtt.subTopic}")
    private String subTopic;
    @Autowired
    private LampService lampService;
    @Autowired
    private LampStatusService lampStatusService;
    // 消息处理
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String s = JSON.toJSONString(message.getHeaders());
        Headers header = JSONObject.parseObject(s, Headers.class);

        boolean equals = subTopic.contains(header.getMqttReceivedTopic());
        //  判断是否是订阅的主题
        if (equals) {
            String s1 = JSON.toJSONString(message.getPayload())
                    .replaceAll("\\\\n|\\\\r|[\r\n]+|\\\\", "")
                    .replaceAll("\uFEFF", "").trim();// 移除所有 \r 和 \n
            // 去除{} 外面的引号
            LampPayload lampPayload = JSONObject.parseObject(s1.substring(1, s1.length() - 1),
                    LampPayload.class
            ) ;
            lampService.updateStatus(lampPayload);
            lampStatusService.updateStatus(lampPayload);
        }

    }
    /*
     * payload：{
     *   "msg": "1111"
     * }
     * message：GenericMessage [payload={
     *   "msg": "1111"
     * }, headers={mqtt_receivedRetained=false, mqtt_id=0, mqtt_duplicate=false, id=81b53bc2-84bd-bc45-b0d8-810f1013e7dd, mqtt_receivedTopic=springboot/a, mqtt_receivedQos=0, timestamp=1745028918489}]
     */
}