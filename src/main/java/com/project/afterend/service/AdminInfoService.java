package com.project.afterend.service;

import com.project.afterend.beans.AdminInfo;
import com.project.afterend.beans.UserInfo;
import com.project.afterend.mapper.AdminInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminInfoService {
    @Autowired
    AdminInfoMapper adminInfoMapper;

    //登录功能
    public AdminInfo getLogin(Map<String,Object> map){
        return adminInfoMapper.getLogin(map);
    }
    //修改密码
    public int updateAP(Map<String,Object> map){
        return adminInfoMapper.updateAP(map);
    }

}
