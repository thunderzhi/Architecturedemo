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

    @Autowired
    private OrderDao orderDao;

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


    //没有事务，部分数据没有rollback
    public long noTransaction()throws Exception {
        Order m = new Order();
        m.setOrderno("444444444");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("noTransaction");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);
        Long id = m.getId();
        m.setId(id);
        m.setUsername("noTransaction");
        int x=1/0;
        //throw new RuntimeException("rollback");
        orderMapper.insert(m);

        System.out.println(JsonUtil.toJson(m));
        return m.getId();
    }
    //REQUIRED 所有数据rollback
    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long reqTransaction()throws Exception {
        Order m = new Order();
        m.setOrderno("444444444");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("aaareqTrans");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);
        Long id = m.getId();
        m.setId(id);
        m.setUsername("bbbreqTransaction");
        int x=1/0;
        //throw new RuntimeException("rollback");
        orderMapper.insert(m);

        System.out.println(JsonUtil.toJson(m));
        return m.getId();
    }

    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long supporttrans() throws Exception {
        return supportTransaction();
    }


    public long supportNotrans() throws Exception {
        return supportTransaction();
    }

    //support 是根据invoke 来决定是否使用事务
    @Transactional(propagation = Propagation.SUPPORTS,transactionManager = "transactionManager")
    public long supportTransaction()throws Exception {
        Order m = new Order();
        m.setOrderno("444444444");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("aasupportTransaction");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);
        Long id = m.getId();
        m.setId(id);
        m.setUsername("bbsupportTransaction");
        int x=1/0;
        //throw new RuntimeException("rollback");
        orderMapper.insert(m);

        System.out.println(JsonUtil.toJson(m));
        return m.getId();
    }

    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long mandatoryparent(){
        Order m = new Order();
        m.setOrderno("666");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("mandatoryparent");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);
        mandatory();
        return m.getId();
    }


    //mandatory 不能单独存在，必须依赖上层调用的transaction
    @Transactional(propagation = Propagation.MANDATORY,transactionManager = "transactionManager")
    public long mandatory(){
        Order m = new Order();
        m.setOrderno("444444444");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("aasumandatory");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);
        Long id = m.getId();
        m.setId(id);
        m.setUsername("bbsumandatory");
        int x=1/0;
        //throw new RuntimeException("rollback");
        orderMapper.insert(m);

        System.out.println(JsonUtil.toJson(m));
        return m.getId();
    }


    //scene first

    //region  error occur in func1 check transaction
    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long requireTransScene11(){
        Order m = new Order();
        m.setOrderno("77777");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("requireTran11");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);
        orderDao.requireNewTransScene12();
        throw new RuntimeException("run");
        //return m.getId();
    }



    //endregion
    //scene second

    //region error occur in func 2 check transaction
    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long requireTransScene21(){
        Order m = new Order();
        m.setOrderno("88888");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("requireTran21");
        m.setAmount(new BigDecimal(100000000));
        orderMapper.insert(m);

        //不catch会导致 主调用方yiqirollback
        try {
            orderDao.requireNewTransScene22();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return m.getId();
    }


    //endregion
}
