package com.project.afterend.mapper;

import com.project.afterend.beans.WeekDiary;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface WeekDiaryMapper {
    List<WeekDiary> getAllWeekDiaryByID(Integer student_id);
    int updateByID(WeekDiary weekDiary);
    ArrayList<WeekDiary> getAllWeekDiary(Map<String, Object> map);
    List<WeekDiary> getAllWeekDiaryByIDS(Integer student_id);
    WeekDiary selectByKey(Integer id);
}
