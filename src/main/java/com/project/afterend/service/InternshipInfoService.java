package com.project.afterend.service;

import com.project.afterend.beans.InternshipInfo;
import com.project.afterend.mapper.InternshipInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InternshipInfoService {
    @Autowired
    InternshipInfoMapper internshipInfoMapper;

    //查询全表(带模糊查询)
    public List<InternshipInfo> selectAll(String query) {
        return internshipInfoMapper.selectAll(query);
    }

    //更新删除状态
    public int updatebydeflag(InternshipInfo internshipInfo) {
        return internshipInfoMapper.updatebydeflag(internshipInfo);
    }

    //根据id查
    public InternshipInfo selectByPrimaryKey(Integer inId) {
        return internshipInfoMapper.selectByPrimaryKey(inId);
    }

    //增加实习
    public int insert(InternshipInfo record) {
        return internshipInfoMapper.insert(record);
    }

    //根据学号查询，防止重复添加
    public InternshipInfo selectBystuid(Integer stuId) {
        return internshipInfoMapper.selectBystuid(stuId);
    }

    //修改信息
    public int updateByPrimaryKey(InternshipInfo record) {
        return internshipInfoMapper.updateByPrimaryKey(record);
    }

    //前台得到某个学生的实习信息
    public InternshipInfo selectByStuNumberFort(String stuNumber) {
        return internshipInfoMapper.selectByStuNumberFort(stuNumber);
    }

    //前台学生提交修改实习信息的请求
    public Integer updateStudentInternshipBystuNum(Map<String, Object> map) {
        return internshipInfoMapper.updateStudentInternshipBystuNum(map);
    }

    //教师获取更在等待审核的学生信息
    public ArrayList<InternshipInfo> selectIntershipChangeInfo(Map<String, Object> map) {
        return internshipInfoMapper.selectIntershipChangeInfo(map);
    }

    //根据学生id获得具体的变更申请
    public InternshipInfo selectIntershipChangeInfoByStuid(Integer id) {
        return internshipInfoMapper.selectIntershipChangeInfoByStuid(id);
    }

    //
    public Integer refusechange(Integer inid) {
        return internshipInfoMapper.refusechange(inid);
    }

    public Integer acceptchange(Map<String, Object> map) {
        return internshipInfoMapper.acceptchange(map);
    }

    public Integer getstudentchangeinfobysignal(Map<String, Object> map) {
        return internshipInfoMapper.getstudentchangeinfobysignal(map);
    }

    public ArrayList<InternshipInfo> selectallIntership(Map<String, Object> map) {
        return internshipInfoMapper.selectallIntership(map);
    }

    public Integer givescore(Map<String, Object> map) {
        return internshipInfoMapper.givescore(map);
    }

    public Integer givescorebyinc(Map<String, Object> map) {
        return internshipInfoMapper.givescorebyinc(map);
    }

    public InternshipInfo selectoneIntershipscore(Integer stuId) {
        return internshipInfoMapper.selectoneIntershipscore(stuId);

    }
    public ArrayList<InternshipInfo> selectSalaryByCollageId(Integer co_id){
        return internshipInfoMapper.selectSalaryByCollageId(co_id);
    }
    public ArrayList<InternshipInfo> selectLocationByCollageId(Integer co_id){
        return internshipInfoMapper.selectLocationByCollageId(co_id);
    }
    public ArrayList<InternshipInfo> selectStudentRankListByGrade(String grade){
        return internshipInfoMapper.selectStudentRankListByGrade(grade);
    }
    public ArrayList<InternshipInfo> selectstudentIntershipscorebyinc(Integer interId){
        return internshipInfoMapper.selectstudentIntershipscorebyinc(interId);
    }
}
