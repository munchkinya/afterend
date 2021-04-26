package com.project.afterend.mapper;

import com.project.afterend.beans.InternshipInfo;
import com.project.afterend.beans.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface InternshipInfoMapper {
    int deleteByPrimaryKey(Integer inId);



    int insertSelective(InternshipInfo record);


    int updateByPrimaryKeySelective(InternshipInfo record);


    List<InternshipInfo> selectAll(String query);//查询全表(带模糊查询)

    int updatebydeflag(InternshipInfo internshipInfo);//更新删除状态

    InternshipInfo selectByPrimaryKey(Integer inId);//根据id查

    int insert(InternshipInfo record);//增加实习

    InternshipInfo selectBystuid(Integer stuId);//根据学号查询，防止重复添加

    int updateByPrimaryKey(InternshipInfo record);//修改信息

    InternshipInfo selectByStuNumberFort(String stuNumber);//前台得到某个学生的实习信息

    Integer updateStudentInternshipBystuNum(Map<String, Object> map);//前台学生提交修改实习信息的请求

    ArrayList<InternshipInfo> selectIntershipChangeInfo(Map<String, Object> map);//教师获取更在等待审核的学生信息

    InternshipInfo selectIntershipChangeInfoByStuid(Integer id);//根据学生id获得具体的变更申请

    Integer refusechange(Integer inid);//

    Integer acceptchange(Map<String, Object> map);

    Integer getstudentchangeinfobysignal(Map<String, Object> map);

    ArrayList<InternshipInfo> selectallIntership(Map<String, Object> map);

    Integer givescore(Map<String, Object> map);

    Integer givescorebyinc(Map<String, Object> map);

    InternshipInfo selectoneIntershipscore(Integer stuId);

    ArrayList<InternshipInfo> selectSalaryByCollageId(Integer co_id);

    ArrayList<InternshipInfo> selectLocationByCollageId(Integer co_id);

    ArrayList<InternshipInfo> selectStudentRankListByGrade(String grade);

    ArrayList<InternshipInfo> selectstudentIntershipscorebyinc(Integer interId);
}