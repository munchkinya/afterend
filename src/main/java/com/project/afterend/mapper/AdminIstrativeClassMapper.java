package com.project.afterend.mapper;

import com.project.afterend.beans.AdminIstrativeClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminIstrativeClassMapper {
    int deleteByPrimaryKey(Integer adId);

    int insert(AdminIstrativeClass record);

    int insertSelective(AdminIstrativeClass record);

    AdminIstrativeClass selectByPrimaryKey(Integer adId);

    int updateByPrimaryKeySelective(AdminIstrativeClass record);

    int updateByPrimaryKey(AdminIstrativeClass record);

    List<AdminIstrativeClass> selectByMajorid(int majid);//根据专业查找班级
}