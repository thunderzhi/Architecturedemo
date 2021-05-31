package com.cxz.mapper.TestDB;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/1/6 22:48
 */
@Repository
public class OrderQueryManager {

    public String selectAll(){
        String sql = new SQL() {{
            SELECT("Id, OrderNo, CreateTime ,DataFlag, UserName");
            FROM("T_Order");
        }}.toString();
        return sql;
    }
}
