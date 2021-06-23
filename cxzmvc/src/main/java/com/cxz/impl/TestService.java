package com.cxz.impl;


import com.cxz.mapper.TestDB.OrderMapper;
import com.cxz.mapper.TestDB.TUserMapper;
import com.cxz.mapper.TestDB2.TOrderRefundMapper;
import com.cxz.model.Order;
import com.cxz.model.TOrderRefund;
import com.cxz.model.TUser;
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
 * @date 2020/11/2 11:04
 */
@Service
public class TestService {

    @Autowired(required = true)
    private TUserMapper tUserMapper;
    @Autowired
    private TOrderRefundMapper tOrderRefundMapper;

    @Autowired
    private OrderMapper orderMapper;

    public String test(){
        return "----------------------";
    }

    public List<TUser> getlist(){
        List<TUser> tUsers = tUserMapper.selectAll();

        return tUsers;

    }

    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class,RuntimeException.class},
            transactionManager = "transactionManager")
    public int multidbinsert(List<Order> orders,List<TOrderRefund> refunds){
        int res =0;
        for (Order order : orders) {
            int i = orderMapper.insert(order);
            res +=i;
            break;

        }
        for (TOrderRefund refund : refunds) {
            int i = tOrderRefundMapper.insert(refund);
            res +=i;
            break;

        }
        int j = 1/0;
        return res;
    }

    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class,RuntimeException.class},
            transactionManager = "transactionManager")
    public int multidbinsertNoErr(List<Order> orders,List<TOrderRefund> refunds){
        int res =0;
        for (Order order : orders) {
            int i = orderMapper.insert(order);
            res +=i;
            break;

        }
        for (TOrderRefund refund : refunds) {
            int i = tOrderRefundMapper.insert(refund);
            res +=i;
            break;

        }

        return res;
    }
}