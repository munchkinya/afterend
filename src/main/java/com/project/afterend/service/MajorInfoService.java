package com.project.afterend.service;

import com.project.afterend.beans.MajorInfo;
import com.project.afterend.mapper.MajorInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorInfoService {
    @Autowired
    MajorInfoMapper majorInfoMapper;
    //根据学院查方向
    public List<MajorInfo> selectByCollegeId(int coid){
        return majorInfoMapper.selectByCollegeId(coid);
    }
}
