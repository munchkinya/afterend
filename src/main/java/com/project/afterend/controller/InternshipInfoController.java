package com.project.afterend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.afterend.beans.*;
import com.project.afterend.service.InternshipCompanyService;
import com.project.afterend.service.InternshipInfoService;
import com.project.afterend.service.SchoolTeacherService;
import com.project.afterend.service.StudentInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/internshipinfo")
public class InternshipInfoController {
    @Autowired
    InternshipInfoService internshipInfoService;
    @Autowired
    InternshipCompanyService internshipCompanyService;
    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    SchoolTeacherService schoolTeacherService;

    //查询全表，得到初始列表（最基本的表）
    @GetMapping(value = "/getallinterinfo")
    public PageInfo<InternshipInfo> getAll(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize, @Valid @RequestParam("query") String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<InternshipInfo> list = internshipInfoService.selectAll(query);
        PageInfo<InternshipInfo> page = new PageInfo(list);
        return page;
    }
    /*老师看学生反馈*/
    @GetMapping(value = "/getallstudentintershipinfofeedback")
    public PageInfo<InternshipInfo> getallstudentintershipinfofeedback(@Valid @RequestParam("pageNum") Integer pageNum,
                                                               @Valid @RequestParam("pageSize") Integer pageSize,
                                                               @Valid @RequestParam("query") Integer query,
                                                               @Valid @RequestParam("stID") String stID) {
        List<Integer> idlist = new ArrayList<>();
        SchoolTeacher schoolTeacher = schoolTeacherService.selectBysteachNumber(stID);
        List<StudentInfo> studentlist=studentInfoService.selectBySTNumber(schoolTeacher.getSteachId());//得到学生列表
        for(StudentInfo studentInfo:studentlist){
            idlist.add(studentInfo.getStuId());
        }
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        ArrayList<InternshipInfo> list = new ArrayList<>();
        if(idlist.size()==0){
            InternshipInfo internshipInfo=new InternshipInfo();
            internshipInfo.setInId(0);
            list.add(internshipInfo);
        }else {
            parameterMap.put("query", query);
            parameterMap.put("list", idlist);
            list= internshipInfoService.selectAllbycom(parameterMap);
        }
        PageInfo<InternshipInfo> page = new PageInfo(list);
        return page;
    }
    /*修改一般发送put请求*/
    /*更新状态，0代表未删除，1代表删除*/
    @PutMapping(value = "/deleinterinfo/{inId}/{delflag}")
    public InternshipInfo delete(@Param("inId") @PathVariable(value = "inId") Integer inId, @PathVariable(value = "delflag") Integer delflag) {
        InternshipInfo internshipInfo=new InternshipInfo();
        internshipInfo.setInId(inId);
        internshipInfo.setDelflag(delflag);
        internshipInfoService.updatebydeflag(internshipInfo);
        return internshipInfoService.selectByPrimaryKey(inId);//这里只返回被修改的一条用户数据
    }
    //查询全部实习公司信息(添加用户时显示)
    @GetMapping(value = "/getallintercompany")
    public List<InternshipCompany> getAll() {
        return internshipCompanyService.selectall();
    }
    //查询学生基本信息,添加实习信息时可以用到
    @GetMapping(value = "/getstudent")
    public StudentInfo getAll(@Valid @RequestParam("stunumber") String stunumber) {
        return studentInfoService.selectByStudentName(stunumber);
    }
    //添加功能
    @GetMapping(value = "/insertinterinfo")
    public String insert(@Valid @RequestParam("stunumber") String stunumber,
                         @Valid @RequestParam("intername") String intername,
                         @Valid @RequestParam("starttime") String starttime,
                         @Valid @RequestParam("endtime") String endtime,
                         @Valid @RequestParam("stuId") String stuId,
                         @Valid @RequestParam("status") String status) throws Exception{
        /*System.out.println(stunumber);
        System.out.println(intername);
        System.out.println(starttime);
        System.out.println(endtime);
        System.out.println(stuId);
        System.out.println(status);*/
        if(internshipInfoService.selectBystuid(Integer.parseInt(stuId))!=null){
            return "again";//用户名重复校验问题
        }
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        InternshipInfo internshipInfo=new InternshipInfo();
        internshipInfo.setStuId(Integer.parseInt(stuId));
        internshipInfo.setInterId(Integer.parseInt(intername));
        internshipInfo.setStarttime(ft.parse(starttime));
        internshipInfo.setEndtime(ft.parse(endtime));
        internshipInfo.setStatus(status);
        if(internshipInfoService.insert(internshipInfo)!=0){
            return "success";//返回成功的标志
        }
        return "fail";
    }

    //修改页面
    @GetMapping(value = "/getOneII")
    public InternshipInfo insert(@Valid @RequestParam("interId") Integer interId) {
        return internshipInfoService.selectByPrimaryKey(interId);
    }

    //修改公司信息（弹出修改页面的修改）
    @GetMapping(value = "/updateii")
    public InternshipInfo update(@Valid @RequestParam("inId") Integer inId,
                                  @Valid @RequestParam("stuId") Integer stuId,
                                 @Valid @RequestParam("interId") Integer interId,
                                  @Valid @RequestParam("starttime") String starttime,
                                  @Valid @RequestParam("endtime") String endtime,
                                  @Valid @RequestParam("status") String status) throws ParseException {
        InternshipInfo internshipInfo=new InternshipInfo();
        //先判断一下重复校验,防止没被修改
        if(internshipInfoService.selectBystuid(stuId)!=null){
            if(internshipInfoService.selectBystuid(stuId).getInId()!=inId){
                internshipInfo.setVueStatus("again");
                return internshipInfo;
            }
        }
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        internshipInfo.setInId(inId);
        internshipInfo.setStuId(stuId);
        internshipInfo.setInterId(interId);
        internshipInfo.setStarttime(ft.parse(starttime));
        internshipInfo.setEndtime(ft.parse(endtime));
        internshipInfo.setStatus(status);
        internshipInfoService.updateByPrimaryKey(internshipInfo);
        return internshipInfoService.selectByPrimaryKey(inId);//这里只返回被修改的一条用户数据
    }

    //前台得到某个学生的实习信息
    @GetMapping(value = "/getastudentIn")
    public InternshipInfo selectByStuNumberFort(@Valid @RequestParam("stuNum") String stuNum) {
        return internshipInfoService.selectByStuNumberFort(stuNum);
    }

    //前台学生提交修改实习信息的请求,老师审核中
    @GetMapping(value = "/updateastudentIn")
    public String updateastudentIn(@Valid @RequestParam("stuNum") String stuNum,
                                           @Valid @RequestParam("new_company") String new_company,
                                           @Valid @RequestParam("new_post") String new_post,
                                           @Valid @RequestParam("new_money") Double new_money,
                                           @Valid @RequestParam("new_starttime") String new_starttime,
                                           @Valid @RequestParam("new_endtime") String new_endtime,
                                           @Valid @RequestParam("new_address") String  new_address,
                                           @Valid @RequestParam("text") String  text) throws ParseException {
        StudentInfo studentInfo=studentInfoService.selectByStudentName(stuNum);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        parameterMap.put("stuNum", studentInfo.getStuId());
        parameterMap.put("new_company", new_company);
        parameterMap.put("new_post", new_post);
        parameterMap.put("new_money", new_money);
        parameterMap.put("new_starttime", ft.parse(new_starttime));
        parameterMap.put("new_endtime", ft.parse(new_endtime));
        parameterMap.put("new_address", new_address);
        parameterMap.put("text", text);
        if(internshipInfoService.updateStudentInternshipBystuNum(parameterMap)==0){
            return "fail";
        }else{
            return "success";
        }
    }

    @GetMapping(value = "/getstudentchangeinfo")
    public PageInfo<InternshipInfo> getstudentchangeinfo(@Valid @RequestParam("pageNum") Integer pageNum,
                                                         @Valid @RequestParam("pageSize") Integer pageSize,
                                                         @Valid @RequestParam("query") String query,
                                                         @Valid @RequestParam("stID") String stID) {
        List<Integer> idlist = new ArrayList<>();
        SchoolTeacher schoolTeacher = schoolTeacherService.selectBysteachNumber(stID);
        List<StudentInfo> studentlist=studentInfoService.selectBySTNumber(schoolTeacher.getSteachId());//得到学生列表
        for(StudentInfo studentInfo:studentlist){
            idlist.add(studentInfo.getStuId());
        }
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        ArrayList<InternshipInfo> list = new ArrayList<>();
        if(idlist.size()==0){
            InternshipInfo internshipInfo=new InternshipInfo();
            internshipInfo.setInId(0);
            list.add(internshipInfo);
        }else {
            parameterMap.put("query", query);
            parameterMap.put("list", idlist);
            list= internshipInfoService.selectIntershipChangeInfo(parameterMap);
        }
        PageInfo<InternshipInfo> page = new PageInfo(list);
        return page;
    }

    //根据学生id查实习变更申请
    @GetMapping(value = "/getstudentchangeinfobystuid")
    public InternshipInfo getstudentchangeinfoByid(@Valid @RequestParam("stuID") Integer stuid) {
        System.out.println(stuid);
        return internshipInfoService.selectIntershipChangeInfoByStuid(stuid);
    }

    //得到老师审核信号
    @GetMapping(value = "/getstudentchangeinfobysignal")
    public String getSignal(@Valid @RequestParam("inid") Integer inid,
                           @Valid @RequestParam("signal") String signal) {
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        parameterMap.put("inid", inid);
        parameterMap.put("signal", signal);
        System.out.println(signal);
        System.out.println(internshipInfoService.getstudentchangeinfobysignal(parameterMap));
        return "success";
    }

    //老师拒绝了学生的申请
    @GetMapping(value = "/refusechange")
    public String refusechange(@Valid @RequestParam("inid") Integer inid) {
        internshipInfoService.refusechange(inid);
        return "success";
    }

    //老师同意了学生的申请
    @GetMapping(value = "/acceptchange")
    public String acceptchange(@Valid @RequestParam("inid") Integer inid) {
        InternshipInfo internshipInfo=internshipInfoService.selectByPrimaryKey(inid);
        InternshipCompany internshipCompany=internshipCompanyService.selectByUserName(internshipInfo.getNew_company());
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        if(internshipCompany!=null){
            parameterMap.put("interid", internshipCompany.getInterId());
        }else{
            InternshipCompany internshipCompany1=new InternshipCompany();
            internshipCompany1.setInterName(internshipInfo.getNew_company());
            internshipCompany1.setInterPassword("123");
            internshipCompany1.setAddress(internshipInfo.getNew_address());
            internshipCompany1.setRoleid(7);
            internshipCompanyService.insert(internshipCompany1);
            parameterMap.put("interid", internshipCompanyService.selectByUserName(internshipInfo.getNew_company()).getInterId());
        }
        parameterMap.put("inid", inid);
        internshipInfoService.acceptchange(parameterMap);
        return "sucess";
    }

    /*实习负责人给学生打实习成绩*/
    @GetMapping(value = "/getallstudentintershipinfobyinc")
    public PageInfo<InternshipInfo> getallstudentintershipinfoby(@Valid @RequestParam("pageNum") Integer pageNum,
                                                         @Valid @RequestParam("pageSize") Integer pageSize,
                                                         @Valid @RequestParam("query") String query,
                                                         @Valid @RequestParam("incID") String incID) {
        List<Integer> idlist = new ArrayList<>();
        InternshipCompany internshipCompany = internshipCompanyService.selectByUserName(incID);
        List<InternshipInfo> internshipInfoList =internshipInfoService.selectstudentIntershipscorebyinc(internshipCompany.getInterId());
        for(InternshipInfo internshipInfo:internshipInfoList){
            idlist.add(internshipInfo.getStuId());
        }
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        ArrayList<InternshipInfo> list = new ArrayList<>();
        if(idlist.size()==0){
            InternshipInfo internshipInfo=new InternshipInfo();
            internshipInfo.setInId(0);
            list.add(internshipInfo);
        }else {
            parameterMap.put("query", query);
            parameterMap.put("list", idlist);
            list= internshipInfoService.selectallIntership(parameterMap);
        }
        PageInfo<InternshipInfo> page = new PageInfo(list);
        return page;
    }

    /*老师给学生打实习成绩*/
    @GetMapping(value = "/getallstudentintershipinfo")
    public PageInfo<InternshipInfo> getallstudentintershipinfo(@Valid @RequestParam("pageNum") Integer pageNum,
                                                               @Valid @RequestParam("pageSize") Integer pageSize,
                                                               @Valid @RequestParam("query") String query,
                                                               @Valid @RequestParam("stID") String stID) {
        List<Integer> idlist = new ArrayList<>();
        SchoolTeacher schoolTeacher = schoolTeacherService.selectBysteachNumber(stID);
        List<StudentInfo> studentlist=studentInfoService.selectBySTNumber(schoolTeacher.getSteachId());//得到学生列表
        for(StudentInfo studentInfo:studentlist){
            idlist.add(studentInfo.getStuId());
        }
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        ArrayList<InternshipInfo> list = new ArrayList<>();
        if(idlist.size()==0){
            InternshipInfo internshipInfo=new InternshipInfo();
            internshipInfo.setInId(0);
            list.add(internshipInfo);
        }else {
            parameterMap.put("query", query);
            parameterMap.put("list", idlist);
            list= internshipInfoService.selectallIntership(parameterMap);
        }
        PageInfo<InternshipInfo> page = new PageInfo(list);
        return page;
    }

    //老师给学生打了成绩
    @GetMapping(value = "/givestudentscore")
    public String givestudentscore(@Valid @RequestParam("inid") Integer inid,@Valid @RequestParam("score") String score) {
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        parameterMap.put("inid", inid);
        parameterMap.put("intershipscoreone", score);
        internshipInfoService.givescore(parameterMap);
        return "success";
    }

    //企业负责人给学生打了成绩
    @GetMapping(value = "/givestudentscorebyinc")
    public String givestudentscorebyinc(@Valid @RequestParam("inid") Integer inid,@Valid @RequestParam("score") String score) {
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        parameterMap.put("inid", inid);
        parameterMap.put("intershipscoretwo", score);
        internshipInfoService.givescorebyinc(parameterMap);
        return "success";
    }

    //学生查看自己的实习成绩
    @GetMapping(value = "/getstudentscore")
    public InternshipInfo getstudentscore(@Valid @RequestParam("stID") String stID) {
        StudentInfo studentInfo=studentInfoService.selectByStudentName(stID);
        return internshipInfoService.selectoneIntershipscore(studentInfo.getStuId());
    }

    //根据学生年级得到排行榜
    @GetMapping(value = "/getranklist")
    public PageInfo<InternshipInfo> getranklist(@Valid @RequestParam("pageNum") Integer pageNum,
                                            @Valid @RequestParam("pageSize") Integer pageSize,
                                            @Valid @RequestParam("query") String query) {
        if("".equals(query)){
            query="2017";
        }
        //然后按照薪资降序排序
        List<InternshipInfo> list = internshipInfoService.selectStudentRankListByGrade(query);
        Comparator<InternshipInfo> comp = new Comparator<com.project.afterend.beans.InternshipInfo>() {
            @Override
            public int compare(InternshipInfo o1, InternshipInfo o2) {
                if(o1.getOld_money()<o2.getOld_money()){
                    return 1;
                }else if(o1.getOld_money()>(o2.getOld_money())){
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(list, comp);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<InternshipInfo> page1 = new PageInfo(list);
        return page1;
    }

    //学生实习反馈
    @PutMapping(value = "/feedback/{inId}/{feedback}")
    public String delete(@Param("inId") @PathVariable(value = "inId") Integer inId, @PathVariable(value = "feedback") String feedback) {
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        parameterMap.put("feedback", feedback);
        parameterMap.put("interId", inId);
        internshipInfoService.givefeedback(parameterMap);
        return "sucess";
    }
}
