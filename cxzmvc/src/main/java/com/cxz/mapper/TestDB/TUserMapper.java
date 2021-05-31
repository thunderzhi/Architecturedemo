package com.cxz.mapper.TestDB;


import com.cxz.model.TUser;
import com.cxz.model.TUserExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TUserMapper {
    long countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    @SelectProvider(type =UserQueryManager.class,method = "selectAll")
    @ResultMap("BaseResultMap")
    List<TUser> selectAll();
}