package com.project.afterend.service;

import com.project.afterend.beans.InternshipCompany;
import com.project.afterend.beans.TrainingTeacher;
import com.project.afterend.mapper.InternshipCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InternshipCompanyService {
    @Autowired
    InternshipCompanyMapper internshipCompanyMapper;
    //实习公司的登录
    public InternshipCompany getLogin(Map<String,Object> map){
        return internshipCompanyMapper.getLogin(map);
    }

    //查询全表(带模糊查询)
    public List<InternshipCompany> selectAll(String query){
        return internshipCompanyMapper.selectAll(query);
    }
    //修改删除状态
    public int updatebydeflag(InternshipCompany internshipCompany){
        return internshipCompanyMapper.updatebydeflag(internshipCompany);
    }
    //根据id
    public InternshipCompany selectByPrimaryKey(Integer interId){
        return internshipCompanyMapper.selectByPrimaryKey(interId);
    }
    //用户名重复校验
    public InternshipCompany selectByUserName(String interName){
        return internshipCompanyMapper.selectByUserName(interName);
    }
    //实现增加
    public int insert(InternshipCompany record){
        return internshipCompanyMapper.insert(record);
    }
    //得到所有公司信息
    public List<InternshipCompany> selectall(){
        return internshipCompanyMapper.selectall();
    }
    //修改
    public int updateByPrimaryKey(InternshipCompany record){
        return internshipCompanyMapper.updateByPrimaryKey(record);
    }
    //得到人数
    public Integer getnum(){
        return internshipCompanyMapper.getnum();
    }
    //得到所有正在招聘的公司
    public List<InternshipCompany> selectInternshipcompany(){
        return internshipCompanyMapper.selectInternshipcompany();
    }
}
