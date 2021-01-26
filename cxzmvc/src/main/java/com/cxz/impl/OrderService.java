package com.cxz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxz.mapper.OrderMapper;
import com.cxz.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/1/25 15:21
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public List<Order> getOrderList(QueryWrapper<Order> qw){
        return orderMapper.selectList(qw);
    }
    public List<Order> OrderListSelectAll(QueryWrapper<Order> qw){
        return orderMapper.selectAll();
    }

    public int add(Order model){
        return orderMapper.insert(model);

    }
}
