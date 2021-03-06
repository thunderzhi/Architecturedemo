package com.cxz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cxz.mapper.OrderMapper;
import com.cxz.model.Order;
import com.cxz.model.vo.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public void addmanyorder(List<Order> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            int add =   orderMapper.insert(list.get(i));
            if (i%5==1){
                throw new RuntimeException("rollback");
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long updatewhere(UpdateWrapper<Order> uw) throws Exception {
        long n = 0;

        long i = orderMapper.update(null,uw);
        return i;
    }
}
