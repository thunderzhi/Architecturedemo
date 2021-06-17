package com.cxz.controller;

import com.cxz.impl.TestService;
import com.cxz.model.Order;
import com.cxz.model.TOrderRefund;
import com.cxz.model.TUser;
import com.cxz.utils.JsonUtil;
import com.cxz.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/2 11:05
 */
@Api(tags = "TEST")
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    public TestService testService;
    @RequestMapping("/gettest")
    public String gettest(){
        return testService.test();
    }

    /*
    get content from request
     */

    public String testreq() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String s = StringUtils.streamToStr(request.getInputStream());
        Map<String, String> map = JsonUtil.parseToMap(s, String.class, String.class);
        return "";
    }
    @RequestMapping(value = "/getUsers", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "getUsers")//swagger 当前接口注解
    public Map<String, List<TUser>> getUsers(){
        HashMap<String, List<TUser>> map = new HashMap<>();
        List<TUser> users = testService.getlist();
        map.put("user",users);
        return map;
    }

    @RequestMapping(value = "/tranMultiDB", method = {RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "tranMultiDB")
    public Map<String, String> TranMultiDB(){

        HashMap<String, String> map = new HashMap<>();
        ArrayList<TOrderRefund> rorders = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        int i = 0;
        while (i<6){
            TOrderRefund rorder = new TOrderRefund();
            rorder.setAmount(new BigDecimal(999));
            //TOrderRefund.setCreatetime(LocalDateTime.now());
            String rorderno = UUID.randomUUID().toString().substring(0,10);
            rorder.setOrderno(rorderno);
            rorders.add(rorder);
            Order order = new Order();
            order.setUsername("cxz");
            order.setCreatetime(LocalDateTime.now());
            String orderno = UUID.randomUUID().toString().substring(0,10);
            order.setOrderno(orderno);
            orders.add(order);
            i++;
        }
        int res  = testService.multidbinsert(orders,rorders);
        map.put("user", String.valueOf(res));
        return map;
    }

}