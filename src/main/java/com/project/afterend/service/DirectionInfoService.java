package com.project.afterend.service;

import com.project.afterend.beans.DirectionInfo;
import com.project.afterend.mapper.DirectionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectionInfoService {
    @Autowired
    DirectionInfoMapper directionInfoMapper;
    //根据实训公司查方向
    public List<DirectionInfo> selectByInfo(Integer trainid){
        return directionInfoMapper.selectByInfo(trainid);
    }
}
