package com.cxz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxz.model.Order;
import org.apache.ibatis.annotations.*;

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
//    @ResultMap("BaseResultMap")
    @Results({@Result(column = "Id",property = "Id"),
            @Result(column = "CreateTime",property = "createtime"),
            @Result(column = "DataFlag",property = "dataflag"),
            @Result(column = "OrderNo",property = "orderno")
    })
    List<Order> selectAll();
}