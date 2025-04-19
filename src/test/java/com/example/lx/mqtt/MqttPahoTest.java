package com.example.lx.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

/**
 * @author lx
 * @date 2025/4/18
 * @description
 */
public class MqttPahoTest {

    private static final String broker = "tcp://IP:PORT";
    private static final String clientId = "java_client_1";
    private static final String topic = "java/a";
    private static final String subTopic = "java/b";
    @Test
    public void connect() throws MqttException {
        // 创建客户端
        MqttClient  mqttClient = new MqttClient(broker, clientId,new MemoryPersistence());
        // 创建连接选项
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("linxi");
        options.setPassword("123456".toCharArray());
        // 创建新的连接
        options.setCleanSession(true);
        // 连接
        mqttClient.connect(options);
        // 处于阻塞
//        while (true){
//        }
    }
    @Test
    public void  sendMessage() throws MqttException {
        MqttClient  mqttClient = new MqttClient(broker, clientId,new MemoryPersistence());
        // 创建连接选项
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("linxi");
        options.setPassword("123456".toCharArray());
        // 创建新的连接
        options.setCleanSession(true);
        // 连接
        mqttClient.connect(options);

        MqttMessage message = new MqttMessage();
        message.setPayload("helloMessage".getBytes());
        message.setQos(0);
        message.setRetained(false);
        mqttClient.publish(topic, "hello".getBytes(), 0, false);
        mqttClient.publish(topic,message);
        // 关闭连接
        mqttClient.disconnect();
        mqttClient.close();
    }
    @Test
    public void  receiveMessage() throws MqttException {
        // 创建客户端
        MqttClient  mqttClient = new MqttClient(broker, clientId,new MemoryPersistence());
        // 创建连接选项
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("linxi");
        options.setPassword("123456".toCharArray());
        // 创建新的连接
        options.setCleanSession(true);
        // 设置回调函数拿到消息
        mqttClient.setCallback(new MqttCallback() {
            // 连接断开
            @Override
            public void connectionLost(Throwable throwable) {
                throwable.printStackTrace();
            }
            // 消息到达 s topic
            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println("messageArrived:"+s+"--"+new String(mqttMessage.getPayload()));
                System.out.println(mqttMessage);
            }
            // 消息发送完成
            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                System.out.println("deliveryComplete");
            }
        });

        // 连接
        mqttClient.connect(options);
        // 订阅
        mqttClient.subscribe(subTopic, 2);
//        messageArrived:java/b--{
//            "msg": "222222"
//        }
//        {
//            "msg": "222222"
//        }
    }
}