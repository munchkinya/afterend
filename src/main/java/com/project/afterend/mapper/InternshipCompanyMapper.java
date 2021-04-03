package com.project.afterend.mapper;

import com.project.afterend.beans.InternshipCompany;
import com.project.afterend.beans.StudentInfo;
import com.project.afterend.beans.TrainingTeacher;
import com.project.afterend.beans.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InternshipCompanyMapper {
    int deleteByPrimaryKey(Integer interId);



    int insertSelective(InternshipCompany record);



    int updateByPrimaryKeySelective(InternshipCompany record);



    InternshipCompany getLogin(Map<String,Object> map);//实习公司的登录

    List<InternshipCompany> selectAll(String query);//查询全表(带模糊查询)

    int updatebydeflag(InternshipCompany internshipCompany);//修改删除状态

    InternshipCompany selectByPrimaryKey(Integer interId);//根据id

    InternshipCompany selectByUserName(String interName);//用户名重复校验

    int insert(InternshipCompany record);//实现增加

    List<InternshipCompany> selectall();//得到所有公司信息

    int updateByPrimaryKey(InternshipCompany record);//修改

    Integer getnum();//得到人数

    List<InternshipCompany> selectInternshipcompany();//得到所有正在招聘的公司
}