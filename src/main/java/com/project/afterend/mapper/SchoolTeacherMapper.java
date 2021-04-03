package com.project.afterend.mapper;


import com.project.afterend.beans.StudentInfo;
import com.project.afterend.beans.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import com.project.afterend.beans.SchoolTeacher;

import java.util.List;
import java.util.Map;

@Mapper
public interface SchoolTeacherMapper {

    int insertSelective(SchoolTeacher record);

    int updateByPrimaryKey(SchoolTeacher record);

    List<SchoolTeacher> selectByCondition(SchoolTeacher schoolTeacher);//有条件查询

    SchoolTeacher getLogin(SchoolTeacher record);//学校老师登录

    List<SchoolTeacher> selectAll(String query);//查询全表(带模糊查询)

    int updatebydeflag(SchoolTeacher schoolTeacher);//修改教师删除状态

    int insert(SchoolTeacher record);//增加教师

    SchoolTeacher selectByPrimaryKey(Integer steachId);//根据id查询教师

    int updateByPrimaryKeySelective(SchoolTeacher record);//修改页面里面修改教师信息

    int upfatestrole(SchoolTeacher schoolTeacher);//给用户分配新的角色

    SchoolTeacher getLogin(Map<String,Object> map);//教师登录

    List<SchoolTeacher> selectByCollegeId(int coId);//根据学院查教师

    SchoolTeacher selectBysteachNumber(String steachNumber);//用户名重复校验

    Integer getnum();//得到人数
}