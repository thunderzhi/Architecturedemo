package com.cxz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxz.mapper.OrderMapper;
import com.cxz.mapper.TOrderRefundMapper;
import com.cxz.model.Order;
import com.cxz.model.TOrderRefund;
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
 * @date 2021/5/27 22:43
 */
@Service
public class OrderRefundService {

    @Autowired
    private TOrderRefundMapper tOrderRefundMapper;

    public List<TOrderRefund> getlist(QueryWrapper<TOrderRefund> qw){
        List<TOrderRefund> tOrderRefunds = tOrderRefundMapper.selectList(qw);
        return tOrderRefunds;
    }

    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager2")
    public void addmanyorder(List<TOrderRefund> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            int add =   tOrderRefundMapper.insert(list.get(i));
            if (i%5==1){
                //throw new RuntimeException("rollback");
            }
        }
    }

}
