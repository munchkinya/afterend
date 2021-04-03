package com.project.afterend.service;

import com.project.afterend.beans.SchoolTeacher;
import com.project.afterend.beans.UserInfo;
import com.project.afterend.mapper.SchoolTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SchoolTeacherService {
    @Autowired
    SchoolTeacherMapper schoolTeacherMapper;
    //查询全表(带模糊查询)
    public List<SchoolTeacher> getAll(String query) {
        return schoolTeacherMapper.selectAll(query);
    }
    //修改教师删除状态
    public int delete(SchoolTeacher schoolTeacher){
        return schoolTeacherMapper.updatebydeflag(schoolTeacher);
    }
    //根据id查询教师
    public SchoolTeacher selectByPrimaryKey(int id){
        return schoolTeacherMapper.selectByPrimaryKey(id);
    }
    //增加教师
    public int insert(SchoolTeacher schoolTeacher){
        return schoolTeacherMapper.insert(schoolTeacher);
    }
    //修改页面里面修改教师信息
    public int updateByPrimaryKeySelective(SchoolTeacher schoolTeacher){
        return schoolTeacherMapper.updateByPrimaryKeySelective(schoolTeacher);
    }
    //给教师分配新的角色
    public int upfatestrole(SchoolTeacher schoolTeacher){
        return schoolTeacherMapper.upfatestrole(schoolTeacher);
    }
    //教师登录
    public SchoolTeacher getLogin(Map<String,Object> map){
        return schoolTeacherMapper.getLogin(map);
    }
    //根据学院查教师
    public List<SchoolTeacher> selectByCollegeId(int coId){
        return schoolTeacherMapper.selectByCollegeId(coId);
    }
    //用户名重复校验
    public SchoolTeacher selectBysteachNumber(String steachNumber){
        return schoolTeacherMapper.selectBysteachNumber(steachNumber);
    }
    //得到人数
    public Integer getnum(){
        return schoolTeacherMapper.getnum();
    }

}
