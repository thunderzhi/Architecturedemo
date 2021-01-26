package com.cxz.controller;

import com.cxz.impl.TestService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


}