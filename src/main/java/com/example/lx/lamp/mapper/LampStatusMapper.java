package com.example.lx.lamp.mapper;

import com.example.lx.lamp.entity.LampStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author lx_yy
* @description 针对表【tb_lamp_status】的数据库操作Mapper
* @createDate 2025-04-19 10:04:02
* @Entity com.example.lx.lamp.entity.LampStatus
*/
public interface LampStatusMapper extends BaseMapper<LampStatus> {

    void updateStatus(@Param("status") Integer light, @Param("deviceId") String deviceId);
}




