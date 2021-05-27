package com.cxz.impl;

import com.cxz.mapper.OrderMapper;
import com.cxz.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/5/27 11:41
 */
@Service
public class Order2Service {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW,transactionManager = "transactionManager")
    public long func6(){
        Order m = new Order();
        m.setOrderno("o2func6");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("o2func6");
        m.setAmount(new BigDecimal(666));
        orderMapper.insert(m);
        int x=1/0;
        return m.getId();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,transactionManager = "transactionManager")
    public long func8(){
        Order m = new Order();
        m.setOrderno("o2func8");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("o2func8");
        m.setAmount(new BigDecimal(888));
        orderMapper.insert(m);

        return m.getId();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED,transactionManager = "transactionManager")
    public long notSupport(){
        Order m = new Order();

        m.setOrderno("notSupport");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("notSupport");
        m.setAmount(new BigDecimal(888));
        orderMapper.insert(m);

        int x=1/0;
        return m.getId();
    }

    @Transactional(propagation = Propagation.NEVER,transactionManager = "transactionManager")
    public long nevertrans(){
        Order m = new Order();
        m.setOrderno("nevertrans");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("nevertrans");
        m.setAmount(new BigDecimal(777));
        orderMapper.insert(m);
        return  m.getId();
    }

    @Transactional(propagation = Propagation.NESTED,transactionManager = "transactionManager")
    public long nested(){
        Order m = new Order();
        m.setOrderno("nested");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("nested");
        m.setAmount(new BigDecimal(777));
        orderMapper.insert(m);
        return  m.getId();
    }

}
