package com.cxz.impl;

import com.cxz.mapper.TestDB.OrderMapper;
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
 * @date 2021/3/22 17:21
 */
@Service
public class OrderDao {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW,transactionManager = "transactionManager")
    public long requireNewTransScene12(){
        Order m = new Order();
        m.setOrderno("77777requireNew");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("requireNewTra12");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);
        return m.getId();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,transactionManager = "transactionManager")
    public long requireNewTransScene22(){
        Order m = new Order();
        m.setOrderno("88888reqnew");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("requireNewTran21");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);
        int x=1/0;
        return m.getId();
    }
}
