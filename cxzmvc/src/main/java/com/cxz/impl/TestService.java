package com.cxz.impl;

import com.cxz.mapper.TUserMapper;
import com.cxz.model.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String test(){
        return "----------------------";
    }

    public List<TUser> getlist(){
        List<TUser> tUsers = tUserMapper.selectAll();

        return tUsers;

    }
}