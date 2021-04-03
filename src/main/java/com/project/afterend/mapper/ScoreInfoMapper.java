package com.project.afterend.mapper;

import com.project.afterend.beans.ScoreInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScoreInfoMapper {
    int deleteByPrimaryKey(Integer scoreId);

    int insert(ScoreInfo record);

    int insertSelective(ScoreInfo record);

    ScoreInfo selectByPrimaryKey(Integer scoreId);

    int updateByPrimaryKeySelective(ScoreInfo record);

    int updateByPrimaryKey(ScoreInfo record);
}