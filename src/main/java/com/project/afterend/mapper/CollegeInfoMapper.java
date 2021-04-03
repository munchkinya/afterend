package com.project.afterend.mapper;

import com.project.afterend.beans.CollegeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollegeInfoMapper {
    int deleteByPrimaryKey(Integer coId);

    int insert(CollegeInfo record);

    int insertSelective(CollegeInfo record);

    CollegeInfo selectByPrimaryKey(Integer coId);

    int updateByPrimaryKeySelective(CollegeInfo record);

    int updateByPrimaryKey(CollegeInfo record);

    List<CollegeInfo> getAllCollage();//得到所有学院

    CollegeInfo selectCollageByStuNum(String stu_number);//<!--根据学生学号查询学生所在学院-->

    CollegeInfo selectCollageByStachNum(String steach_number);

    CollegeInfo selectCollageByTrainNum(String tteach_number);
}