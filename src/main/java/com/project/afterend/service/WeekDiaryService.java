package com.project.afterend.service;

import com.project.afterend.beans.WeekDiary;
import com.project.afterend.mapper.WeekDiaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WeekDiaryService {
    @Autowired
    WeekDiaryMapper weekDiaryMapper;
    public List<WeekDiary> getAllWeekDiaryByID(Integer student_id){
        return weekDiaryMapper.getAllWeekDiaryByID(student_id);
    }
    public int updateByID(WeekDiary weekDiary){
        return weekDiaryMapper.updateByID(weekDiary);
    }
    public ArrayList<WeekDiary> getAllWeekDiary(Map<String, Object> map){
        return weekDiaryMapper.getAllWeekDiary(map);
    }
    public WeekDiary selectByKey(Integer id){
        return weekDiaryMapper.selectByKey(id);
    }

}
