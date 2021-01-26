package com.cxz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxz.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxz
 * @since 2021-01-17
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order>{


    @SelectProvider(type =OrderQueryManager.class,method = "selectAll")
    @ResultMap("BaseResultMap")
    List<Order> selectAll();
}