package com.project.afterend.mapper;

import com.project.afterend.beans.StudentInfo;
import com.project.afterend.beans.TrainingCompany;
import com.project.afterend.beans.TrainingTeacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrainingCompanyMapper {
    int deleteByPrimaryKey(Integer trainId);



    int insertSelective(TrainingCompany record);



    int updateByPrimaryKeySelective(TrainingCompany record);



    List<TrainingCompany> selectAll(String query);//查询全表(带模糊查询)

    int updatebydeflag(TrainingCompany trainingCompany);//修改删除状态

    TrainingCompany selectByPrimaryKey(Integer trainId);//根据id

    int insert(TrainingCompany record);//实现增加功能

    TrainingCompany selectByUserName(String trainName);//用户名重复校验问题

    List<TrainingCompany> selectByCollId(Integer collageid);//根据学院id查公司信息

    int updateByPrimaryKey(TrainingCompany record);//修改信息
}