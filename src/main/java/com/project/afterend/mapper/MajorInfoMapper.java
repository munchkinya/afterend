package com.project.afterend.mapper;

import com.project.afterend.beans.MajorInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorInfoMapper {
    int deleteByPrimaryKey(Integer majId);

    int insert(MajorInfo record);

    int insertSelective(MajorInfo record);

    MajorInfo selectByPrimaryKey(Integer majId);

    int updateByPrimaryKeySelective(MajorInfo record);

    int updateByPrimaryKey(MajorInfo record);

    List<MajorInfo> selectByCollegeId(int coid);//根据学院查方向
}