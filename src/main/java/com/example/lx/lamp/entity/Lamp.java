package com.example.lx.lamp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @TableName tb_lamp
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="tb_lamp")
@Data
public class Lamp extends BaseEntity {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String deviceId;

    /**
     * 1上线0.下线
     */
    private Integer status;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}