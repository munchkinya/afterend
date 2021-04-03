package com.project.afterend.mapper;

import com.project.afterend.beans.DirectionClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DirectionClassMapper {
    int deleteByPrimaryKey(Integer dclassId);

    int insert(DirectionClass record);

    int insertSelective(DirectionClass record);

    DirectionClass selectByPrimaryKey(Integer dclassId);

    int updateByPrimaryKeySelective(DirectionClass record);

    int updateByPrimaryKey(DirectionClass record);

    List<DirectionClass> selectByInfo(Integer diid);//根据方向信息查班级
}