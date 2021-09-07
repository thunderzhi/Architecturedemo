package com.cxz.impl;


import com.cxz.mapper.TestDB.TLeecodeMapper;
import com.cxz.mapper.TestDB.OrderMapper;
import com.cxz.mapper.TestDB.TUserMapper;
import com.cxz.mapper.TestDB2.TOrderRefundMapper;
import com.cxz.model.Order;
import com.cxz.model.TLeecode;
import com.cxz.model.TOrderRefund;
import com.cxz.model.TUser;
import com.cxz.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/2 11:04
 */
@Service
public class TestService {

    @Autowired(required = true)
    private TUserMapper tUserMapper;
    @Autowired
    private TOrderRefundMapper tOrderRefundMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TLeecodeMapper tLeecodeMapper;


    public String test(){
        return "----------------------";
    }

    public List<TUser> getlist(){
        List<TUser> tUsers = tUserMapper.selectAll();

        return tUsers;

    }

    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class,RuntimeException.class},
            transactionManager = "transactionManager")
    public int multidbinsert(List<Order> orders,List<TOrderRefund> refunds){
        int res =0;
        for (Order order : orders) {
            int i = orderMapper.insert(order);
            res +=i;
            break;

        }
        for (TOrderRefund refund : refunds) {
            int i = tOrderRefundMapper.insert(refund);
            res +=i;
            break;

        }
        int j = 1/0;
        return res;
    }

    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class,RuntimeException.class},
            transactionManager = "transactionManager")
    public int multidbinsertNoErr(List<Order> orders,List<TOrderRefund> refunds){
        int res =0;
        for (Order order : orders) {
            int i = orderMapper.insert(order);
            res +=i;
            break;

        }
        for (TOrderRefund refund : refunds) {
            int i = tOrderRefundMapper.insert(refund);
            res +=i;
            break;

        }

        return res;
    }

    public int batchAddLC(){
        String path = "D:\\xuezhi\\Documents\\20210825LeeCodeEasy";
        String txt = readfile(path);
        int i  =0;
        List<TLeecode> tLeecodes = JsonUtil.toList(TLeecode.class, txt);
        for (TLeecode item : tLeecodes) {
            i+=tLeecodeMapper.insert(item);
        }

        return i;
    }

    public static String readfile(String fileName ){
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {

            reader = new BufferedReader(new FileReader(file));


            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号

                line++;
                sb.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }

    public void test2(){}



}