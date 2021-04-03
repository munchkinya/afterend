package com.project.afterend.mapper;

import com.project.afterend.beans.ResumeInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface ResumeInfoMapper {
    List<ResumeInfo> getAllResumeByStuid(Integer stu_id);
    List<ResumeInfo> getAllResumeByInterid(Integer intercom_id);
    Integer insertResume(ResumeInfo resumeInfo);
    ResumeInfo getResumeByName(String filename);
    Integer changeResumeStatus(ResumeInfo resumeInfo);
    ResumeInfo selectById(Integer id);
    ArrayList<ResumeInfo> getResumeByST(Map<String,Object> map);
}
