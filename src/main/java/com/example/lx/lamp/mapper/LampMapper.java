package com.example.lx.lamp.mapper;

import com.example.lx.lamp.entity.Lamp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author lx_yy
* @description 针对表【tb_lamp】的数据库操作Mapper
* @createDate 2025-04-19 10:04:02
* @Entity com.example.lx.lamp.entity.Lamp
*/
public interface LampMapper extends BaseMapper<Lamp> {

    void updateStatus(@Param("status") Integer line,@Param("deviceId") String deviceId);
}




