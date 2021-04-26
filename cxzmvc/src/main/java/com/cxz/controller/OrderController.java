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

        @RequestMapping(value = "/addmanyScore", method = {RequestMethod.GET})
        @ApiOperation(httpMethod = "GET", value = "addmanyScore")
        public Map<String, String> addmanyScore(){
                ArrayList<TScore> scores = new ArrayList<>();
                int i = 0;
                while (i<6){
                        TScore score = new TScore();
                        score.setStuno(UUID.randomUUID().toString());
                        Random r = new Random();

                        score.setA(r.nextInt(100));
                        score.setB(r.nextInt(100));
                        score.setC(r.nextInt(100));
                        score.setD(r.nextInt(100));


                        scores.add(score);
                        i++;
                }
                try {
                        orderService.addmanyScores(scores);
                } catch (Exception e) {
                        e.printStackTrace();
                }

                Map<String, String> map = new HashMap<>();
                map.put("200", JsonUtil.toJson(scores));
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

                uw.set("Amount",1.6);
                uw.last(" LIMIT 1");
                List<Order> orderList = orderService.getOrderList(qw);
                long res = orderService.updatewhere(uw);
                Map<String, String> map = new HashMap<>();
                map.put("200", String.valueOf(res));
                return map;
        }

        //没有事务
        @RequestMapping(value = "/noTransaction", method = {RequestMethod.POST})
        @ApiOperation(httpMethod = "POST", value = "noTransaction")//swagger 当前接口注解
        public Map<String, String> noTransaction() throws Exception {
                long l = orderService.noTransaction();
                Map<String, String> map = new HashMap<>();
                map.put("200", String.valueOf(l));
                return map;
        }
        @RequestMapping(value = "/reqTransaction", method = {RequestMethod.POST})
        @ApiOperation(httpMethod = "POST", value = "reqTransaction")//swagger 当前接口注解
        public Map<String, String> reqTransaction() throws Exception {
                long l = orderService.reqTransaction();
                Map<String, String> map = new HashMap<>();
                map.put("200", String.valueOf(l));
                return map;
        }

        @RequestMapping(value = "/supportTransaction", method = {RequestMethod.POST})
        @ApiOperation(httpMethod = "POST", value = "supportTransaction")//swagger 当前接口注解
        public Map<String, String> supportTransaction() throws Exception {
                long l = orderService.supporttrans();
                Map<String, String> map = new HashMap<>();
                map.put("200", String.valueOf(l));
                return map;
        }

        @RequestMapping(value = "/supportNoTransaction", method = {RequestMethod.POST})
        @ApiOperation(httpMethod = "POST", value = "supportNoTransaction")//swagger 当前接口注解
        public Map<String, String> supportNoTransaction() throws Exception {
                long l = orderService.supportNotrans();
                Map<String, String> map = new HashMap<>();
                map.put("200", String.valueOf(l));
                return map;
        }

        @RequestMapping(value = "/mandatory", method = {RequestMethod.POST})
        @ApiOperation(httpMethod = "POST", value = "mandatory")//swagger 当前接口注解
        public Map<String, String> mandatory() throws Exception {
                long l = orderService.mandatory();
                Map<String, String> map = new HashMap<>();
                map.put("200", String.valueOf(l));
                return map;
        }

        @RequestMapping(value = "/mandatoryparent", method = {RequestMethod.POST})
        @ApiOperation(httpMethod = "POST", value = "mandatoryparent")//swagger 当前接口注解
        public Map<String, String> mandatoryparent() throws Exception {
                long l = orderService.mandatoryparent();
                Map<String, String> map = new HashMap<>();
                map.put("200", String.valueOf(l));
                return map;
        }

        @RequestMapping(value = "/requireTransScene1", method = {RequestMethod.POST})
        @ApiOperation(httpMethod = "POST", value = "requireTransScene1")//swagger 当前接口注解
        public Map<String, String> requireTransScene1() throws Exception {
                long l = orderService.requireTransScene11();
                Map<String, String> map = new HashMap<>();
                map.put("200", String.valueOf(l));
                return map;
        }

        @RequestMapping(value = "/requireTransScene2", method = {RequestMethod.POST})
        @ApiOperation(httpMethod = "POST", value = "requireTransScene2")//swagger 当前接口注解
        public Map<String, String> requireTransScene2() throws Exception {
                long l = orderService.requireTransScene21();
                Map<String, String> map = new HashMap<>();
                map.put("200", String.valueOf(l));
                return map;
        }
}