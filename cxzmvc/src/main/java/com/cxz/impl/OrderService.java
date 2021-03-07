package com.cxz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cxz.mapper.OrderMapper;
import com.cxz.model.Order;
import com.cxz.model.vo.OrderRequest;
import com.cxz.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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


    public long noTransaction()throws Exception {
        Order m = new Order();
        m.setOrderno("444444444");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("aaaaaaa");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);
        Long id = m.getId();
        m.setId(id);
        m.setUsername("bbbbbbb");
        int x=1/0;
        //throw new RuntimeException("rollback");
        orderMapper.insert(m);

        System.out.println(JsonUtil.toJson(m));
        return m.getId();
    }
    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long reqTransaction()throws Exception {
        Order m = new Order();
        m.setOrderno("444444444");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("aaaaaaa");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);
        Long id = m.getId();
        m.setId(id);
        m.setUsername("bbbbbbb");
        int x=1/0;
        //throw new RuntimeException("rollback");
        orderMapper.insert(m);

        System.out.println(JsonUtil.toJson(m));
        return m.getId();
    }

    @Transactional(propagation = Propagation.SUPPORTS,transactionManager = "transactionManager")
    public long supportTransaction()throws Exception {
        Order m = new Order();
        m.setOrderno("444444444");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("aaaaaaa");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);
        Long id = m.getId();
        m.setId(id);
        m.setUsername("bbbbbbb");
        int x=1/0;
        //throw new RuntimeException("rollback");
        orderMapper.insert(m);

        System.out.println(JsonUtil.toJson(m));
        return m.getId();
    }

}
