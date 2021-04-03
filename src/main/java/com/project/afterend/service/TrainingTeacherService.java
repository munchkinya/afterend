package com.project.afterend.service;

import com.project.afterend.beans.StudentInfo;
import com.project.afterend.beans.TrainingCompany;
import com.project.afterend.beans.TrainingTeacher;
import com.project.afterend.beans.UserInfo;
import com.project.afterend.mapper.TrainingTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TrainingTeacherService {
    @Autowired
    TrainingTeacherMapper trainingTeacherMapper;
    //实训教师登录
    public TrainingTeacher getLogin(Map<String,Object> map){
        return trainingTeacherMapper.getLogin(map);
    }

    //查询全表(带模糊查询)
    public List<TrainingTeacher> selectAll(String query){
        return trainingTeacherMapper.selectAll(query);
    }
    //修改删除状态
    public int updatebydeflag(TrainingTeacher trainingTeacher){
        return trainingTeacherMapper.updatebydeflag(trainingTeacher);
    }
    //根据id
    public TrainingTeacher selectByPrimaryKey(Integer tteachId){
        return trainingTeacherMapper.selectByPrimaryKey(tteachId);
    }
    //增加功能
    public int insert(TrainingTeacher record){
        return trainingTeacherMapper.insert(record);
    }
    //根据用户名查，重复校验问题
    public TrainingTeacher selectByUserName(String tteachNumber){
        return trainingTeacherMapper.selectByUserName(tteachNumber);
    }
    //根据班级查教师，在公司和方向已经确定的情况下
    public List<TrainingTeacher> selectBydclssid(Integer dclassid){
        return trainingTeacherMapper.selectBydclssid(dclassid);
    }
    //修改信息
    public int updateByPrimaryKey(TrainingTeacher record){
        return trainingTeacherMapper.updateByPrimaryKey(record);
    }
    //修改角色
    public int updaterole(TrainingTeacher record){
        return trainingTeacherMapper.updaterole(record);
    }
    //得到人数
    public Integer getnum(){
        return trainingTeacherMapper.getnum();
    }
}
