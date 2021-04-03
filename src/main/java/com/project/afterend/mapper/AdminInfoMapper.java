package com.project.afterend.mapper;

import com.project.afterend.beans.AdminInfo;
import com.project.afterend.beans.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);

    AdminInfo getLogin(Map<String,Object> map);//登录功能

    int updateAP(Map<String,Object> map);//修改密码
}