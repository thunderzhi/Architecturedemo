package com.cxz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cxz.impl.OrderService;
import com.cxz.model.*;
import com.cxz.model.vo.OrderRequest;
import com.cxz.utils.JsonUtil;
import com.cxz.utils.LogUtil;
import com.cxz.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;

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
                        //qw.eq("OrderNo",req.getOrderno());
                        orderList = orderService.getOrderList(qw);
                        map = new HashMap<>(6);
                } catch (Exception e) {
                        e.printStackTrace();
                }

                map.put("order",orderList);
                return map;
        }

        @RequestMapping(value = "/OrderListSelectAll", method = {RequestMethod.GET})
        @ApiOperation(httpMethod = "GET", value = "OrderListSelectAll")//swagger 当前接口注解
        public Map<String, List<Order>> OrderListSelectAll(OrderRequest req){
                List<Order> orderList = null;
                HashMap<String, List<Order>> map = null;
                try {
                        LogUtil.WriteLog(MessageFormat.format("request: {0}",  JsonUtil.toJson(req)));
                        QueryWrapper<Order> qw = new QueryWrapper<>();
                        qw.eq("OrderNo",req.getOrderno());
                        orderList = orderService.OrderListSelectAll(qw);
                        map = new HashMap<>(6);
                } catch (Exception e) {
                        e.printStackTrace();
                }

                map.put("order",orderList);
                return map;
        }
        @RequestMapping(value = "/add", method = {RequestMethod.GET})
        @ApiOperation(httpMethod = "GET", value = "add")
        public Map<String, String> add(){
                Order order = new Order();
                order.setUsername("cxz");

                String orderno = UUID.randomUUID().toString().substring(0,10);
                order.setOrderno(orderno);
                int add = orderService.add(order);
                Map<String, String> map = new HashMap<>();
                map.put("200", JsonUtil.toJson(order));
                return map;
        }

        @RequestMapping(value = "/addmany", method = {RequestMethod.GET})
        @ApiOperation(httpMethod = "GET", value = "addmany")
        public Map<String, String> addmany(){
                ArrayList<Order> orders = new ArrayList<>();
                int i = 0;
                while (i<6){
                        Order order = new Order();
                        order.setUsername("cxz");
                        order.setCreatetime(LocalDateTime.now());
                        String orderno = UUID.randomUUID().toString().substring(0,10);
                        order.setOrderno(orderno);
                        orders.add(order);
                        i++;
                }

                System.out.println(" sys: "+JsonUtil.toJson(orders));

                try {
                        orderService.addmanyorder(orders);
                } catch (Exception e) {
                        e.printStackTrace();
                }

                //
                Map<String, String> map = new HashMap<>();
                map.put("200", JsonUtil.toJson(orders));
                return map;
        }
        @RequestMapping(value = "/Updatewhere", method = {RequestMethod.POST})
        @ApiOperation(httpMethod = "POST", value = "updatewhere")//swagger 当前接口注解
        public Map<String, String> updatewhere(OrderRequest req) throws Exception {
                QueryWrapper<Order> qw = new QueryWrapper<>();
                UpdateWrapper<Order> uw = new UpdateWrapper<>();
                if (!StringUtils.isBlank(req.getOrderno())){
                   qw.eq("OrderNo",req.getOrderno());
                   uw.eq("OrderNo",req.getOrderno());
                }
                if (req.getId()!=null&& req.getId()>0){
                        qw.eq("Id",req.getId());
                        uw.eq("Id",req.getId());
                }
                if (!StringUtils.isBlank(req.getName())){
                        qw.eq("UserName",req.getName());
                }

                uw.set("Amount",3.6);
                uw.last(" LIMIT 1 ");
                List<Order> orderList = orderService.getOrderList(qw);
                long res = orderService.updatewhere(uw);
                Map<String, String> map = new HashMap<>();
                map.put("200", String.valueOf(res));
                return map;
        }

        @RequestMapping(value = "/updatewherein", method = {RequestMethod.POST})
        @ApiOperation(httpMethod = "POST", value = "updatewherein")//swagger 当前接口注解
        public Map<String, String> updatewherein(OrderRequest req) throws Exception {
                QueryWrapper<Order> qw = new QueryWrapper<>();
                UpdateWrapper<Order> uw = new UpdateWrapper<>();
//                if (!StringUtils.isBlank(req.getOrderno())){
//                        qw.eq("OrderNo",req.getOrderno().split(","));
//                        uw.eq("OrderNo",req.getOrderno().split(","));
//                }
                //uw.eq("OrderNo",req.getOrderno().split(","));

//                if (req.getId()!=null&& req.getId()>0){
//                        qw.eq("Id",req.getId());
//                        uw.eq("Id",req.getId());
//                }
//                if (!StringUtils.isBlank(req.getName())){
//                        qw.eq("UserName",req.getName());
//                }

                uw.set("Amount",66.6);
                uw.in("OrderNo",req.getOrderno().split(","));
                //uw.last(" LIMIT 10 ");
                List<Order> orderList = orderService.getOrderList(qw);
                long res = orderService.updatewhere(uw);
                Map<String, String> map = new HashMap<>();
                map.put("200", String.valueOf(res));
                return map;
        }

         

}