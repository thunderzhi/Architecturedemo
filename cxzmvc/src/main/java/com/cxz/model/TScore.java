package com.cxz.model;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-04-25
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("T_Score")
public class TScore implements Serializable {

        private static final long serialVersionUID=1L;

        @TableField("StuNo")
            private String stuno;

        @TableField("A")
            private Integer a;

        @TableField("B")
            private Integer b;

        @TableField("C")
            private Integer c;

        @TableField("D")
            private Integer d;


    }