package com.example.lx.lamp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lx.lamp.constant.LXConstants;
import com.example.lx.lamp.entity.Lamp;
import com.example.lx.lamp.entity.LampStatus;
import com.example.lx.lamp.service.LampService;
import com.example.lx.lamp.mapper.LampMapper;
import com.example.lx.mqtt.entity.LampPayload;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
* @author lx_yy
* @description 针对表【tb_lamp】的数据库操作Service实现
* @createDate 2025-04-19 10:04:02
*/
@Service
public class LampServiceImpl extends ServiceImpl<LampMapper, Lamp>
    implements LampService{
    @Autowired
    private LampMapper lampMapper;
    @Override
    public void updateStatus(LampPayload lampPayload) {
        // 防止脏数据 有多条
        List<Lamp> lamps = lampMapper.selectList(new LambdaQueryWrapper<Lamp>()
                .eq(Lamp::getDeviceId, lampPayload.getDeviceId()));
        Lamp lamp = new Lamp();
        // 如果deviceId第一次传则是新增
        if (lamps.size() == LXConstants.Integer_ZERO) {
            BeanUtils.copyProperties(lampPayload,lamp);
            lamp.setStatus(lampPayload.getLine());
            lampMapper.insert(lamp);
        }else {
            lampMapper.updateStatus(lampPayload.getLine(),lampPayload.getDeviceId());
        }
    }
}




