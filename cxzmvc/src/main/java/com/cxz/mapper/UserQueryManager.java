package com.cxz.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Service;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/1/6 22:48
 */
@Service
public class UserQueryManager {

    public String selectAll(){
        String sql = new SQL() {{
            SELECT("Id, Name,Age,Birth");
            FROM("T_User");
        }}.toString();
        return sql;
    }
}
