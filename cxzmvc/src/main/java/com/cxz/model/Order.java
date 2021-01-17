package com.cxz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
        @TableName("Order")
public class Order implements Serializable {

private static final long serialVersionUID=1L;

                @TableField("OrderNo")
                    private String orderno;

                @TableField("CreateTime")
                    private LocalDateTime createtime;

                @TableField("UserName")
                    private String username;


        }