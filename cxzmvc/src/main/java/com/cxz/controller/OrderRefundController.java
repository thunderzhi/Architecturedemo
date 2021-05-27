package com.cxz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxz.impl.OrderRefundService;
import com.cxz.model.Order;
import com.cxz.model.TOrderRefund;
import com.cxz.model.vo.OrderRequest;
import com.cxz.utils.JsonUtil;
import com.cxz.utils.LogUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/5/27 22:35
 */
@Api(tags = "OrderRefund")
@RestController
@RequestMapping("//orderrefund")
public class OrderRefundController {

    @Autowired
    private OrderRefundService orderRefundService;


    @RequestMapping(value = "/getOrderRefundList", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "getOrderRefundList")//swagger 当前接口注解
    public Map<String, List<TOrderRefund>> getOrderList(OrderRequest req){
        List<TOrderRefund> orderList = null;
        HashMap<String, List<TOrderRefund>> map = null;
        try {
            LogUtil.WriteLog(MessageFormat.format("request: {0}",  JsonUtil.toJson(req)));
            QueryWrapper<TOrderRefund> qw = new QueryWrapper<>();
            //qw.eq("OrderNo",req.getOrderno());
            orderList = orderRefundService.getlist(qw);
            map = new HashMap<>(6);
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("orderrefund",orderList);
        return map;
    }

    @RequestMapping(value = "/addmany", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "addmany")
    public Map<String, String> addmany(){
        ArrayList<TOrderRefund> orders = new ArrayList<>();
        int i = 0;
        while (i<6){
            TOrderRefund order = new TOrderRefund();
            order.setAmount(new BigDecimal(999));
            //TOrderRefund.setCreatetime(LocalDateTime.now());
            String orderno = UUID.randomUUID().toString().substring(0,10);
            order.setOrderno(orderno);
            orders.add(order);
            i++;
        }

        System.out.println(" sys: "+JsonUtil.toJson(orders));

        try {
            orderRefundService.addmanyorder(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //
        Map<String, String> map = new HashMap<>();
        map.put("200", JsonUtil.toJson(orders));
        return map;
    }




}
