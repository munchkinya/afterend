package com.project.afterend.service;

import com.project.afterend.beans.StudentInfo;
import com.project.afterend.beans.TrainingCompany;
import com.project.afterend.beans.TrainingTeacher;
import com.project.afterend.mapper.TrainingCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingCompanyService {
    @Autowired
    TrainingCompanyMapper trainingCompanyMapper;

    //查询全表(带模糊查询)
    public List<TrainingCompany> selectAll(String query){
        return trainingCompanyMapper.selectAll(query);
    }
    //修改删除状态
    public int updatebydeflag(TrainingCompany trainingCompany){
        return trainingCompanyMapper.updatebydeflag(trainingCompany);
    }
    //根据id查
    public TrainingCompany selectByPrimaryKey(Integer trainId){
        return trainingCompanyMapper.selectByPrimaryKey(trainId);
    }
    //实现增加功能
    public int insert(TrainingCompany record){
        return trainingCompanyMapper.insert(record);
    }
    //用户名重复校验问题
    public TrainingCompany selectByUserName(String trainName){
        return trainingCompanyMapper.selectByUserName(trainName);
    }
    //根据学院id查公司信息
    public List<TrainingCompany> selectByCollId(Integer collageid){
        return trainingCompanyMapper.selectByCollId(collageid);
    }
    //修改信息
    public int updateByPrimaryKey(TrainingCompany record){
        return trainingCompanyMapper.updateByPrimaryKey(record);
    }
}
