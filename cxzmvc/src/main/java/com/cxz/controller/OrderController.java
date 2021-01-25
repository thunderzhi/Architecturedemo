package com.cxz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxz.impl.OrderService;
import com.cxz.model.*;
import com.cxz.model.vo.OrderRequest;
import com.cxz.utils.JsonUtil;
import com.cxz.utils.LogUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxz
 * @since 2021-01-17
 */
@Api(tags = "Order")
@RestController
@RequestMapping("//order")
public class OrderController {

        @Autowired
        private OrderService orderService;


        @RequestMapping(value = "/getOrderList", method = {RequestMethod.GET})
        @ApiOperation(httpMethod = "GET", value = "getOrderList")//swagger 当前接口注解
        public Map<String, List<Order>> getOrderList(OrderRequest req){
                List<Order> orderList = null;
                HashMap<String, List<Order>> map = null;
                try {
                        LogUtil.WriteLog(MessageFormat.format("request: {0}",  JsonUtil.toJson(req)));
                        QueryWrapper<Order> qw = new QueryWrapper<>();
                        qw.eq("OrderNo",req.getOrderno());
                        orderList = orderService.getOrderList(qw);
                        map = new HashMap<>(6);
                } catch (Exception e) {
                        e.printStackTrace();
                }

                map.put("order",orderList);
                return map;
        }


}