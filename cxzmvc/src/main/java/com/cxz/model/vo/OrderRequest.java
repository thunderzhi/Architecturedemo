package com.cxz.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/1/25 15:29
 */
@Data
public class OrderRequest  {

    private String orderno;

    private String name;

    private LocalDateTime createtime;

    private BigDecimal amount;

    private Long id;
}
