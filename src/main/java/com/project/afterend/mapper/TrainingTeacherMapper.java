package com.project.afterend.mapper;

import com.project.afterend.beans.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrainingTeacherMapper {
    int deleteByPrimaryKey(Integer tteachId);

    int insertSelective(TrainingTeacher record);

    int updateByPrimaryKeySelective(TrainingTeacher record);


    TrainingTeacher getLogin(Map<String,Object> map);//实训教师登录

    List<TrainingTeacher> selectAll(String query);//查询全表(带模糊查询)

    int updatebydeflag(TrainingTeacher trainingTeacher);//修改删除状态

    TrainingTeacher selectByPrimaryKey(Integer tteachId);//根据id

    int insert(TrainingTeacher record);//增加功能

    TrainingTeacher selectByUserName(String tteachNumber);//根据用户名查，重复校验问题

    List<TrainingTeacher> selectBydclssid(Integer dclassid);//根据班级查教师，在公司和方向已经确定的情况下

    int updateByPrimaryKey(TrainingTeacher record);//修改信息

    int updaterole(TrainingTeacher record);//修改角色

    Integer getnum();//得到人数

}