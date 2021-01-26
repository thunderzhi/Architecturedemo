package com.cxz.model;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author cxz
 * @since 2021-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_Order")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "Id",type = IdType.AUTO)
    private Long Id;


    @TableField("OrderNo")
    private String orderno;

    @TableField("CreateTime")
    private Date createtime;

    @TableField("UserName")
    private String username;


}