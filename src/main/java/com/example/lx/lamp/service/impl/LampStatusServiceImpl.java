package com.example.lx.lamp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lx.lamp.constant.LXConstants;
import com.example.lx.lamp.entity.Lamp;
import com.example.lx.lamp.entity.LampStatus;
import com.example.lx.lamp.mapper.LampMapper;
import com.example.lx.lamp.service.LampStatusService;
import com.example.lx.lamp.mapper.LampStatusMapper;
import com.example.lx.mqtt.entity.LampPayload;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lx_yy
* @description 针对表【tb_lamp_status】的数据库操作Service实现
* @createDate 2025-04-19 10:04:02
*/
@Service
public class LampStatusServiceImpl extends ServiceImpl<LampStatusMapper, LampStatus>
    implements LampStatusService{

    @Autowired
    private LampStatusMapper lampStatusMapper;
    @Autowired
    private LampMapper lampMapper;
    @Override
    public void updateStatus(LampPayload lampPayload) {
        // 1.直接入库 一个lamp有记录时
//        LampStatus lampStatus = new LampStatus();
//        BeanUtils.copyProperties(lampPayload,lampStatus);
//        lampStatus.setStatus(lampPayload.getLight());
//        lampStatusMapper.insert(lampStatus);
//         2.更新lamp状态
        List<Lamp> lamps = lampMapper.selectList(new LambdaQueryWrapper<Lamp>()
                .eq(Lamp::getDeviceId, lampPayload.getDeviceId()));
                //.eq(Lamp::getStatus, LXConstants.LAMP_ONLINE_STATUS)); // 只有在线才可以控制灯的开关
        if (lamps.isEmpty()) {
            return;
        }
        List<LampStatus> list = lampStatusMapper.selectList(new LambdaQueryWrapper<LampStatus>()
                .eq(LampStatus::getDeviceId, lampPayload.getDeviceId()));
        if (list.isEmpty()) {
            LampStatus lampStatus = new LampStatus();
            BeanUtils.copyProperties(lampPayload,lampStatus);
            lampStatus.setStatus(lampPayload.getLight());
            lampStatusMapper.insert(lampStatus);
        }
       lampStatusMapper.updateStatus(lampPayload.getLight(), lampPayload.getDeviceId());
    }
}




