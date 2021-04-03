package com.project.afterend.service;

import com.project.afterend.beans.ResumeInfo;
import com.project.afterend.mapper.ResumeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ResumeInfoService {
    @Autowired
    ResumeInfoMapper resumeInfoMapper;
    public List<ResumeInfo> getAllResumeByStuid(Integer stu_id){
        return resumeInfoMapper.getAllResumeByStuid(stu_id);
    }
    public Integer insertResume(ResumeInfo resumeInfo){
        return resumeInfoMapper.insertResume(resumeInfo);
    }
    public ResumeInfo getResumeByName(String filename){
        return resumeInfoMapper.getResumeByName(filename);
    }
    public List<ResumeInfo> getAllResumeByInterid(Integer intercom_id){
        return resumeInfoMapper.getAllResumeByInterid(intercom_id);
    }
    public Integer changeResumeStatus(ResumeInfo resumeInfo){
        return resumeInfoMapper.changeResumeStatus(resumeInfo);
    }
    public ResumeInfo selectById(Integer id){
        return resumeInfoMapper.selectById(id);
    }
    public ArrayList<ResumeInfo> getResumeByST(Map<String, Object> map){
        return resumeInfoMapper.getResumeByST(map);
    }
}
