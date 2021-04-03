package com.project.afterend.service;

import com.project.afterend.beans.CollegeInfo;
import com.project.afterend.mapper.CollegeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {
    @Autowired
    CollegeInfoMapper collegeInfoMapper;
    public List<CollegeInfo> getAllCollage(){
        return collegeInfoMapper.getAllCollage();
    }
    //查询学生所在学院
    public CollegeInfo selectCollageByStuNum(String stu_number){
        return collegeInfoMapper.selectCollageByStuNum(stu_number);
    }
    public CollegeInfo selectCollageByStachNum(String steach_number){
        return collegeInfoMapper.selectCollageByStachNum(steach_number);
    }
    public CollegeInfo selectCollageByTrainNum(String tteach_number){
        return collegeInfoMapper.selectCollageByTrainNum(tteach_number);
    }
}
