package com.cxz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cxz.mapper.OrderMapper;
import com.cxz.mapper.TScoreMapper;
import com.cxz.model.Order;
import com.cxz.model.TScore;
import com.cxz.model.vo.OrderRequest;
import com.cxz.utils.JsonUtil;
import io.netty.util.internal.UnstableApi;
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
    private TScoreMapper scoreMapper;

    @Autowired
    private Order2Service order2Service;

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
    public void addmanyScores(List<TScore> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            int add =   scoreMapper.insert(list.get(i));

        }
    }

    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long updatewhere(UpdateWrapper<Order> uw) throws Exception {
        long n = 0;

        long i = orderMapper.update(null,uw);
        return i;
    }

    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long updatewherein(UpdateWrapper<Order> uw) throws Exception {
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


        //不catch会导致调用方yiqirollback
        try {
            orderDao.requireNewTransScene22();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return m.getId();
    }


    //endregion
    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long func1(){
        Order m = new Order();
        m.setOrderno("6666666");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("func1");
        m.setAmount(new BigDecimal(1111));

        func2();
        orderMapper.insert(m);
        int x=1/0;

        return m.getId();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,transactionManager = "transactionManager")
    public long func2(){
        Order m = new Order();
        m.setOrderno("777777");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("func2");
        m.setAmount(new BigDecimal(2222));
        orderMapper.insert(m);
        return m.getId();
    }


    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long func3(){
        Order m = new Order();
        m.setOrderno("6666666");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("func1");
        m.setAmount(new BigDecimal(1111));
        orderMapper.insert(m);
        func4();


        return m.getId();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,transactionManager = "transactionManager")
    public long func4(){
        Order m = new Order();
        m.setOrderno("777777");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("func2");
        m.setAmount(new BigDecimal(2222));
        orderMapper.insert(m);
        int x=1/0;
        return m.getId();
    }


    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long func5(){
        Order m = new Order();
        m.setOrderno("555555");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("func1");
        m.setAmount(new BigDecimal(55555));
        orderMapper.insert(m);
        order2Service.func6();


        return m.getId();
    }
    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long func7(){
        Order m = new Order();
        order2Service.func8();
        m.setOrderno("o2func7");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("o2func7");
        m.setAmount(new BigDecimal(777));
        orderMapper.insert(m);
        int x=1/0;

        return m.getId();
    }

    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long outNotSupport(){
        Order m = new Order();

        m.setOrderno("outNotSupport");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("outNotSupport");
        m.setAmount(new BigDecimal(777));
        orderMapper.insert(m);
        notSupport();
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

    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long outNotSupport2(){
        Order m = new Order();
        m.setOrderno("outNotSupport");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("outNotSupport");
        m.setAmount(new BigDecimal(777));
        orderMapper.insert(m);
        order2Service.notSupport();
        return m.getId();
    }

    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long outnevertrans(){
        Order m = new Order();
        m.setOrderno("outnevertrans");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("outnevertrans");
        m.setAmount(new BigDecimal(777));
        orderMapper.insert(m);
        order2Service.nevertrans();
        return  m.getId();
    }

    @Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
    public long outnestnoTran(){
        Order m = new Order();
        m.setOrderno("outnestnoTran");
        m.setCreatetime(LocalDateTime.now());
        m.setDataflag(1);
        m.setUsername("outsetTran");
        m.setAmount(new BigDecimal(777));
        order2Service.nested();
        orderMapper.insert(m);

        int i = 1/0;

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
        int i = 1/0;
        return  m.getId();
    }

}
