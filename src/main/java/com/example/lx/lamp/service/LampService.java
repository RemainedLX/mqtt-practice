package com.example.lx.lamp.service;

import com.example.lx.lamp.entity.Lamp;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lx.mqtt.entity.LampPayload;

/**
* @author lx_yy
* @description 针对表【tb_lamp】的数据库操作Service
* @createDate 2025-04-19 10:04:02
*/
public interface LampService extends IService<Lamp> {

    void updateStatus(LampPayload lampPayload);
}
