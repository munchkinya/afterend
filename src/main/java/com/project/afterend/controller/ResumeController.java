package com.project.afterend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.afterend.beans.*;
import com.project.afterend.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student/resume")
public class ResumeController {
    @Autowired
    ResumeInfoService resumeInfoService;
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private InternshipCompanyService internshipCompanyService;
    @Autowired
    SchoolTeacherService schoolTeacherService;
    @Autowired
    TrainingTeacherService trainingTeacherService;

    @GetMapping(value = "/getAllResumes")
    public PageInfo<ResumeInfo> getAll(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize, @Valid @RequestParam("stuId") String stuId) {
        Integer stu_id=studentInfoService.selectByStudentName(stuId).getStuId();//这里不是学号，是表的主键
        PageHelper.startPage(pageNum, pageSize);
        List<ResumeInfo> list = resumeInfoService.getAllResumeByStuid(stu_id);
        PageInfo<ResumeInfo> page = new PageInfo(list);
        return page;
    }

    /*招聘公司查看自己所收到的简历*/
    @GetMapping(value = "/getAllResumesByInterCom")
    public PageInfo<ResumeInfo> getAllResumes(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize, @Valid @RequestParam("intercomID") String intercomID) {
        Integer intercom_id=internshipCompanyService.selectByUserName(intercomID).getInterId();//这里不是名称，是表的主键
        PageHelper.startPage(pageNum, pageSize);
        List<ResumeInfo> list = resumeInfoService.getAllResumeByInterid(intercom_id);
        PageInfo<ResumeInfo> page = new PageInfo(list);
        return page;
    }

    @PutMapping(value = "/changestatus/{id}/{changestatus}")
    public Integer change(@Param("id") @PathVariable(value = "id") Integer id, @PathVariable(value = "changestatus") String  changestatus) {
        ResumeInfo resumeInfo=new ResumeInfo();
        resumeInfo.setId(id);
        resumeInfo.setStatus(changestatus);
        return resumeInfoService.changeResumeStatus(resumeInfo);
    }

    /*教师查看自己所带学生的简历信息，要分角色*/
    @GetMapping(value = "/getresumesbyst")
    public PageInfo<ResumeInfo> getResumesByST(@Valid @RequestParam("pageNum") Integer pageNum,
                                              @Valid @RequestParam("pageSize") Integer pageSize,
                                              @Valid @RequestParam("stID") String stID,
                                               @Valid @RequestParam("query") String query,
                                               @Valid @RequestParam("roleId") Integer roleId) {
        List<Integer> idlist = null;
        if(roleId == 3 || roleId==4) {//学校教师
            idlist = new ArrayList<>();
            SchoolTeacher schoolTeacher = schoolTeacherService.selectBysteachNumber(stID);
            List<StudentInfo> studentlist=studentInfoService.selectBySTNumber(schoolTeacher.getSteachId());//得到学生列表
            for(StudentInfo studentInfo:studentlist){
                idlist.add(studentInfo.getStuId());
            }
        }else{
            idlist = new ArrayList<>();
            TrainingTeacher trainingTeacher = trainingTeacherService.selectByUserName(stID);
            List<StudentInfo> studentlist=studentInfoService.selectByTTNumber(trainingTeacher.getTteachId());//得到学生列表
            for(StudentInfo studentInfo:studentlist){
                idlist.add(studentInfo.getStuId());
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        ArrayList<ResumeInfo> list = new ArrayList<>();
        if(idlist.size()==0){
            ResumeInfo resumeInfo=new ResumeInfo();
            resumeInfo.setId(0);
            list.add(resumeInfo);
        }else {
            parameterMap.put("query", query);
            parameterMap.put("list", idlist);
            list=resumeInfoService.getResumeByST(parameterMap);
        }
        PageInfo<ResumeInfo> page = new PageInfo(list);
        return page;
    }
}
