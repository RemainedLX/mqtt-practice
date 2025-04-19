# mqtt-practice
智能灯泡练习(shangguigu)
## 环境
* MySQL 5.x
* SpringBoot 2.x
* Java 1.8
* mybatis-plus 3.5
## 目录结构
```
sql   -- sql文件
lamp  -- 灯泡业务
mqtt  -- mqtt连接服务   
        config 配置文件
        entity 处理消息的实体
        gateway 网关
        handler 处理消息的handler
        service 发消息的服务
 test 下 com.example.lx.mqtt   测试MQTT连接
```
## 主要业务

* 1.设备Id首次收到MQTT消息会直接存储到数据库中，后续收到消息会更新数据库中的数据 
* 2.收到MQTT消息发的状态会直接入库操作，可能收到的状态和数据库的状态是一致的但仍会执行SQL （可用Redis减少入库）。
* 3.可用接口向MQTT发消息，内容为此时数据库表中的状态。
* 4.案例

**收到消息**
```
{
  "line":1, //在线状态
  "light": 1, //开关状态
  "deviceId": "10000" //设备Id
}
```
发送消息接口： /lamp/all/10000

**发送消息内容**
```
{
    "deviceId": "10000",
    "lightStatus": 1,
    "lineStatus": 1
}
```
注：和sgg不太一样，但MQTT配置基本相同