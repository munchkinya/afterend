package com.project.afterend.mapper;

import com.project.afterend.beans.DirectionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DirectionInfoMapper {
    int deleteByPrimaryKey(Integer diId);

    int insert(DirectionInfo record);

    int insertSelective(DirectionInfo record);

    DirectionInfo selectByPrimaryKey(Integer diId);

    int updateByPrimaryKeySelective(DirectionInfo record);

    int updateByPrimaryKey(DirectionInfo record);

    List<DirectionInfo> selectByInfo(Integer trainid);//根据实训公司查方向
}