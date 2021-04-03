package com.project.afterend.service;

import com.project.afterend.beans.AdminIstrativeClass;
import com.project.afterend.mapper.AdminIstrativeClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminIstrativeClassService {
    @Autowired
    AdminIstrativeClassMapper adminIstrativeClassMapper;
    //根据专业查找班级
    public List<AdminIstrativeClass> selectByMajorid(int majid){
        return adminIstrativeClassMapper.selectByMajorid(majid);
    }
}
