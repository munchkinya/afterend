package com.project.afterend.mapper;

import com.project.afterend.beans.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentInfoMapper {
    int deleteByPrimaryKey(Integer stuId);



    int insertSelective(StudentInfo record);



    int updateByPrimaryKeySelective(StudentInfo record);



    List<StudentInfo> selectAll();

    public StudentInfo getLogin(Map<String,Object> map);//学生登录

    List<SchoolTeacher> selectByCondition(SchoolTeacher schoolTeacher);//有条件查询

    List<StudentInfo> selectAll(String query);//查询全表(带模糊查询)

    int updatebydeflag(StudentInfo studentInfo);//修改学生删除状态

    StudentInfo selectByPrimaryKey(Integer stuId);//根据id查

    int insert(StudentInfo record);//增加学生

    StudentInfo selectByStudentName(String stuNumber);//根据用户名查，重复校验问题

    int updateByPrimaryKey(StudentInfo record);//修改信息

    Integer getnum();//得到人数

    List<StudentInfo> selectBySTNumber(Integer id);//新，根据学院普通教师查学生

    List<StudentInfo> selectByCollage(Integer id);//根据学院领导查学生

    List<StudentInfo> selectByTTNumber(Integer id);//根据实训教师查学生

    List<StudentInfo> selectByTrainCom(Integer id);//根据实训企业领导查学生，这里就是整个公司的学生

    List<StudentInfo> selectAllGrade();

}