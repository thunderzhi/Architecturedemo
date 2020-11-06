package com.cxz.controller;

import com.cxz.impl.RedisService;
import com.cxz.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/5 16:38
 */
@RestController
@RequestMapping("/redis")
public class redisopController {
    private static final Logger logger = Logger.getLogger(redisopController.class);
    @Autowired
    public RedisService redisService;

    @RequestMapping("/index")
    public ModelAndView index2(Model model) {
        model.addAttribute("msg","Hello Spring MVC!");

        return new ModelAndView("redis/redisindex");
    }

    @RequestMapping(value = "/set", method = {RequestMethod.POST,RequestMethod.GET})
    //@ResponseBody
    public Map<String, String> addkey(@RequestParam("key")  String key) throws JsonProcessingException {
        User u = new User();
        HashMap json = new HashMap();
        String dateStr = Long.toString(System.currentTimeMillis()/1000L);
        u.setAge(dateStr);
        u.setName(key);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonstr = objectMapper.writeValueAsString(u);

        //String key = "cxzmvc"+dateStr;
        //Timestamp time1 = new Timestamp(System.currentTimeMillis());
        boolean b = redisService.setStr(key,u);
        logger.debug("set is executed!");
        json.put("success", String.valueOf(b));
        return json;
    }
}
