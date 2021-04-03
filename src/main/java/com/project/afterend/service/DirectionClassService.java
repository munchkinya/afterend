package com.project.afterend.service;

import com.project.afterend.beans.DirectionClass;
import com.project.afterend.mapper.DirectionClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectionClassService {
    @Autowired
    DirectionClassMapper directionClassMapper;
    //根据方向信息查班级
    public List<DirectionClass> selectByInfo(Integer diid){
        return directionClassMapper.selectByInfo(diid);
    }

}
