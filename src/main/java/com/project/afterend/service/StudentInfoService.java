package com.project.afterend.service;

import com.project.afterend.beans.CollegeInfo;
import com.project.afterend.beans.InternshipInfo;
import com.project.afterend.beans.StudentInfo;
import com.project.afterend.beans.UserInfo;
import com.project.afterend.mapper.StudentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentInfoService {

    @Autowired
    StudentInfoMapper studentInfoMapper;

    public List<StudentInfo> list() {

        return studentInfoMapper.selectAll();
    }

    public boolean deleteByPrimaryKey(Integer primaryKey) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setAdId(primaryKey);
        studentInfo.setDelflag(1);
        boolean res = studentInfoMapper.updateByPrimaryKeySelective(studentInfo) != 0;
        return res;
    }
    //学生登录
    public StudentInfo getLogin(Map<String,Object> map){
        return studentInfoMapper.getLogin(map);
    }

    //查询全表(带模糊查询)
    public List<StudentInfo> selectAll(String query){
        return studentInfoMapper.selectAll(query);
    }

    //修改学生删除状态
    public int updatebydeflag(StudentInfo studentInfo){
        return studentInfoMapper.updatebydeflag(studentInfo);
    }
    //根据id查
    public StudentInfo selectByPrimaryKey(Integer stuId){
        return studentInfoMapper.selectByPrimaryKey(stuId);
    }
    //增加学生
    public int insert(StudentInfo record){
        return studentInfoMapper.insert(record);
    }
    //根据用户名查，重复校验问题
    public StudentInfo selectByStudentName(String stuNumber){
        return studentInfoMapper.selectByStudentName(stuNumber);
    }
    //修改信息
    public int updateByPrimaryKey(StudentInfo record){
        return studentInfoMapper.updateByPrimaryKey(record);
    }
    //得到人数
    public Integer getnum(){
        return studentInfoMapper.getnum();
    }
    //新，根据学院普通教师查学生
    public List<StudentInfo> selectBySTNumber(Integer id){
        return studentInfoMapper.selectBySTNumber(id);
    }
    //根据学院领导查学生
    public List<StudentInfo> selectByCollage(Integer id){
        return studentInfoMapper.selectByCollage(id);
    }
    //根据实训教师查学生
    public List<StudentInfo> selectByTTNumber(Integer id){
        return studentInfoMapper.selectByTTNumber(id);
    }
    //根据实训企业领导查学生，这里就是整个公司的学生
    public List<StudentInfo> selectByTrainCom(Integer id){
        return studentInfoMapper.selectByTrainCom(id);
    }
    public List<StudentInfo> selectAllGrade(){
        return studentInfoMapper.selectAllGrade();
    }
}
