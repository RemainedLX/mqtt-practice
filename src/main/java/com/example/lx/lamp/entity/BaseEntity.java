package com.example.lx.lamp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {
    @TableLogic(value = "0")
    private Integer delFlag;
    @TableField("create_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)//序列化器
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)//反序列化器
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//时间格式(含有日期和时间)
    private LocalDateTime createTime;
    @TableField("update_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)//序列化器
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)//反序列化器
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//时间格式(含有日期和时间)
    private LocalDateTime updateTime;
}
