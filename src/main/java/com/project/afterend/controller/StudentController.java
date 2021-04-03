package com.project.afterend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.afterend.beans.*;
import com.project.afterend.model.vo.studentthree.CollageStudentThreeAdd;
import com.project.afterend.model.vo.studentthree.SchoolTeacherStudentAdd;
import com.project.afterend.model.vo.studentvo.CollageStudentAdd;
import com.project.afterend.model.vo.studentvo.MajorClassStudentAdd;
import com.project.afterend.model.vo.studentvo.MajorStudentAdd;
import com.project.afterend.model.vo.studentvotwo.*;
import com.project.afterend.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    MajorInfoService majorInfoService;
    @Autowired
    AdminIstrativeClassService adminIstrativeClassService;
    @Autowired
    TrainingCompanyService trainingCompanyService;
    @Autowired
    DirectionInfoService directionInfoService;
    @Autowired
    DirectionClassService directionClassService;
    @Autowired
    TrainingTeacherService trainingTeacherService;
    @Autowired
    SchoolTeacherService schoolTeacherService;

    //查询学生全表，得到初始列表（最基本的表）
    @GetMapping(value = "/getallstudents")
    public PageInfo<StudentInfo> getAll(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize, @Valid @RequestParam("query") String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentInfo> list = studentInfoService.selectAll(query);
        PageInfo<StudentInfo> page = new PageInfo(list);
        return page;
    }
    /*修改一般发送put请求*/
    /*更新状态，0代表未删除，1代表删除*/
    @PutMapping(value = "/delestudent/{stuId}/{delflag}")
    public StudentInfo delete(@Param("stuId") @PathVariable(value = "stuId") Integer stuId, @PathVariable(value = "delflag") Integer delflag) {
        StudentInfo studentInfo=new StudentInfo();
        studentInfo.setDelflag(delflag);
        studentInfo.setStuId(stuId);
        studentInfoService.updatebydeflag(studentInfo);
        return studentInfoService.selectByPrimaryKey(stuId);//这里只返回被修改的一条用户数据
    }
    //添加对话框里面的第一个级联选择器
    @GetMapping(value = "/getselectedOptions1")
    public List<CollageStudentAdd> getall1() {
        List<CollegeInfo> collegeInfoList=collegeService.getAllCollage();
        List<CollageStudentAdd> collageStudentAddList=new ArrayList<>();//真正返回的集合
        for(int i=0;i<collegeInfoList.size();i++) {
            CollageStudentAdd collageStudentAdd=new CollageStudentAdd();
            collageStudentAdd.setId(collegeInfoList.get(i).getCoId());
            collageStudentAdd.setName(collegeInfoList.get(i).getCoName());
            Integer coid=collegeInfoList.get(i).getCoId();
            List<MajorInfo> majorInfoList=majorInfoService.selectByCollegeId(coid);
            List<MajorStudentAdd> majorStudentAddList=new ArrayList<>();
            for(int j=0;j<majorInfoList.size();j++){
                MajorStudentAdd majorStudentAdd=new MajorStudentAdd();
                majorStudentAdd.setId(majorInfoList.get(j).getMajId());
                majorStudentAdd.setName(majorInfoList.get(j).getMajName());
                Integer majid=majorInfoList.get(j).getMajId();
                List<AdminIstrativeClass> adminIstrativeClassList=adminIstrativeClassService.selectByMajorid(majid);
                List<MajorClassStudentAdd> majorClassStudentAddList=new ArrayList<>();
                for(int k=0;k<adminIstrativeClassList.size();k++){
                    MajorClassStudentAdd majorClassStudentAdd=new MajorClassStudentAdd();
                    majorClassStudentAdd.setId(adminIstrativeClassList.get(k).getAdId());
                    majorClassStudentAdd.setName(adminIstrativeClassList.get(k).getAdName());
                    majorClassStudentAddList.add(majorClassStudentAdd);
                }
                majorStudentAdd.setChildren(majorClassStudentAddList);
                majorStudentAddList.add(majorStudentAdd);
            }
            collageStudentAdd.setChildren(majorStudentAddList);
            collageStudentAddList.add(collageStudentAdd);
        }
        return collageStudentAddList;
    }
    //添加对话框里面的第二个级联选择器
    @GetMapping(value = "/getselectedOptions2")
    public List<CollageAdd> getall2() {
        List<CollegeInfo> collegeInfoList=collegeService.getAllCollage();
        List<CollageAdd> collageAddList=new ArrayList<>();//真正返回的集合
        for(int i=0;i<collegeInfoList.size();i++){
            //先赋值给工具类，然后再进行查找，下面几个类都是这个套路
            CollageAdd collageAdd=new CollageAdd();
            collageAdd.setId(collegeInfoList.get(i).getCoId());
            collageAdd.setName(collegeInfoList.get(i).getCoName());
            Integer collageid=collegeInfoList.get(i).getCoId();
            List<TrainingCompany> trainingCompanyList=trainingCompanyService.selectByCollId(collageid);
            List<TrainingCompanyAdd> trainingCompanyAddList=new ArrayList<>();
            for(int j=0;j<trainingCompanyList.size();j++){
                //这里得是根据公司查方向
                TrainingCompanyAdd trainingCompanyAdd=new TrainingCompanyAdd();//当前等待赋值对象
                TrainingCompany trainingCompany=trainingCompanyList.get(j);//当前循环对象
                trainingCompanyAdd.setId(trainingCompany.getTrainId());
                trainingCompanyAdd.setName(trainingCompany.getTrainName());
                Integer trainid=trainingCompany.getTrainId();
                List<DirectionInfo> directionInfoList=directionInfoService.selectByInfo(trainid);
                List<DirectionInfoAdd> directionInfoAddList=new ArrayList<>();
                //这里是最后一层，根据方向找班级
                for(int k=0;k<directionInfoList.size();k++){
                    DirectionInfoAdd directionInfoAdd=new DirectionInfoAdd();
                    DirectionInfo directionInfo=directionInfoList.get(k);
                    directionInfoAdd.setId(directionInfo.getDiId());
                    directionInfoAdd.setName(directionInfo.getDiName());
                    Integer diid=directionInfo.getDiId();
                    List<DirectionClass> directionClassList=directionClassService.selectByInfo(diid);
                    List<DirectionClassAdd> directionClassAddList=new ArrayList<>();
                    //最后一遍得加个循环复制
                    for(int l=0;l<directionClassList.size();l++){
                        DirectionClass directionClass=directionClassList.get(l);
                        DirectionClassAdd directionClassAdd=new DirectionClassAdd();
                        directionClassAdd.setId(directionClass.getDclassId());
                        directionClassAdd.setName(directionClass.getDclassName());
                        Integer dclassid=directionClass.getDclassId();
                        List<TrainingTeacher> trainingTeacherList=trainingTeacherService.selectBydclssid(dclassid);
                        List<TrainTeacherAdd> trainTeacherAddList=new ArrayList<>();
                        for(int m=0;m<trainingTeacherList.size();m++) {
                            TrainTeacherAdd trainTeacherAdd=new TrainTeacherAdd();
                            trainTeacherAdd.setId(trainingTeacherList.get(m).getTteachId());
                            trainTeacherAdd.setName(trainingTeacherList.get(m).getTteachName());
                            trainTeacherAddList.add(trainTeacherAdd);
                        }
                        directionClassAdd.setChildren(trainTeacherAddList);
                        directionClassAddList.add(directionClassAdd);
                    }
                    directionInfoAdd.setChildren(directionClassAddList);
                    directionInfoAddList.add(directionInfoAdd);
                }
                trainingCompanyAdd.setChildren(directionInfoAddList);
                trainingCompanyAddList.add(trainingCompanyAdd);
            }
            collageAdd.setChildren(trainingCompanyAddList);
            collageAddList.add(collageAdd);
        }
        return collageAddList;
    }
    //添加对话框里面的第三个级联选择器
    @GetMapping(value = "/getselectedOptions3")
    public List<CollageStudentThreeAdd> getall3() {
        List<CollegeInfo> collegeInfoList=collegeService.getAllCollage();
        List<CollageStudentThreeAdd> collageStudentThreeAddList=new ArrayList<>();//真正返回的集合
        for(int i=0;i<collegeInfoList.size();i++){
            //先赋值给工具类，然后再进行查找，下面几个类都是这个套路
            CollageStudentThreeAdd collageStudentThreeAdd=new CollageStudentThreeAdd();
            collageStudentThreeAdd.setId(collegeInfoList.get(i).getCoId());
            collageStudentThreeAdd.setName(collegeInfoList.get(i).getCoName());
            Integer coId=collegeInfoList.get(i).getCoId();
            List<SchoolTeacher> schoolTeacherList=schoolTeacherService.selectByCollegeId(coId);
            List<SchoolTeacherStudentAdd> schoolTeacherStudentAddList=new ArrayList<>();//真正返回的集合
            for(int j=0;j<schoolTeacherList.size();j++){
                SchoolTeacherStudentAdd schoolTeacherStudentAdd=new SchoolTeacherStudentAdd();
                schoolTeacherStudentAdd.setId(schoolTeacherList.get(j).getSteachId());
                schoolTeacherStudentAdd.setName(schoolTeacherList.get(j).getSteachName());
                schoolTeacherStudentAddList.add(schoolTeacherStudentAdd);
            }
            collageStudentThreeAdd.setChildren(schoolTeacherStudentAddList);
            collageStudentThreeAddList.add(collageStudentThreeAdd);
        }

        return collageStudentThreeAddList;
    }
    //添加功能
    @GetMapping(value = "/insertstudent")
    public String insert(@Valid @RequestParam("stuNumber") String stuNumber,
                         @Valid @RequestParam("stuPassword") String stuPassword,
                         @Valid @RequestParam("stuName") String stuName,
                         @Valid @RequestParam("stuSex") String stuSex,
                         @Valid @RequestParam("stuEmail") String stuEmail,
                         @Valid @RequestParam("stuTel") String stuTel,
                         @Valid @RequestParam("grade") Integer grade,
                         @Valid @RequestParam("selectedOptions1") String selectedOptions1,
                         @Valid @RequestParam("selectedOptions2") String selectedOptions2,
                         @Valid @RequestParam("selectedOptions3") String selectedOptions3) {
        String options1[]=selectedOptions1.split(",");
        String options2[]=selectedOptions2.split(",");
        String options3[]=selectedOptions3.split(",");
        if(studentInfoService.selectByStudentName(stuNumber)!=null){
            return "again";//用户名重复校验问题
        }
        StudentInfo studentInfo=new StudentInfo();
        studentInfo.setStuNumber(stuNumber);
        studentInfo.setStuPassword(stuPassword);
        studentInfo.setStuName(stuName);
        studentInfo.setStuSex(stuSex);
        studentInfo.setStuEmail(stuEmail);
        studentInfo.setStuTel(stuTel);
        studentInfo.setGrade(grade);
        studentInfo.setAdId(Integer.parseInt(options1[2]));//专业班级id
        studentInfo.setDclassId(Integer.parseInt(options2[3]));//方向班级id
        studentInfo.setSteachid(Integer.parseInt(options2[4]));//企业教师id
        studentInfo.setTteachid(Integer.parseInt(options3[1]));//校内指导教师id
        if(studentInfoService.insert(studentInfo)!=0){
            return "success";//返回成功的标志
        }
        return "fail";
    }

    //根据id查学生
    @GetMapping(value = "/getstudentbyid")
    public StudentInfo getone(@Valid @RequestParam("id") String id) {
        return studentInfoService.selectByPrimaryKey(Integer.parseInt(id));
    }

    //修改信息（弹出修改页面的修改）
    @PutMapping(value = "/updatestudent/{stuId}/{stuNumber}/{stuPassword}/{stuName}/{stuSex}/{stuEmail}/{stuTel}/{adId}/{dclassId}/{steachid}/{tteachid}/{grade}")
    public StudentInfo update(@Param("stuId") @PathVariable(value = "stuId") Integer stuId,
                                @PathVariable(value = "stuNumber") String stuNumber,
                              @PathVariable(value = "stuPassword") String stuPassword,
                                @PathVariable(value = "stuName") String stuName,
                              @PathVariable(value = "stuSex") String stuSex,
                                @PathVariable(value = "stuEmail") String stuEmail,
                              @PathVariable(value = "stuTel") String stuTel,
                              @PathVariable(value = "adId") Integer adId,
                              @PathVariable(value = "dclassId") Integer dclassId,
                              @PathVariable(value = "steachid") Integer steachid,
                              @PathVariable(value = "tteachid") Integer tteachid,
                              @PathVariable(value = "grade") Integer grade

    ) {
        StudentInfo studentInfo=new StudentInfo();
        //先判断一下学号重复校验,防止学号没被修改
        if(studentInfoService.selectByStudentName(stuNumber)!=null){
            if(studentInfoService.selectByStudentName(stuNumber).getStuId()!=stuId){
                studentInfo.setVueStatus("again");
                return studentInfo;
            }
        }
        studentInfo.setStuId(stuId);
        studentInfo.setStuNumber(stuNumber);
        studentInfo.setStuName(stuName);
        studentInfo.setStuPassword(stuPassword);
        studentInfo.setStuSex(stuSex);
        studentInfo.setStuEmail(stuEmail);
        studentInfo.setStuTel(stuTel);
        studentInfo.setAdId(adId);
        studentInfo.setDclassId(dclassId);
        studentInfo.setSteachid(steachid);
        studentInfo.setTteachid(tteachid);
        studentInfo.setGrade(grade);
        studentInfoService.updateByPrimaryKey(studentInfo);
        return studentInfoService.selectByPrimaryKey(stuId);//这里只返回被修改的一条用户数据
    }
    //得到全部学生年级
    @GetMapping(value = "/getallgrade")
    public List<StudentInfo> getallgrade() {
        return studentInfoService.selectAllGrade();
    }
}
