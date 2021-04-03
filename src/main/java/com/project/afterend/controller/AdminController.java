package com.project.afterend.controller;

import com.project.afterend.beans.*;
import com.project.afterend.model.vo.AdminPicture;
import com.project.afterend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminInfoService adminInfoService;
    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    InternshipCompanyService internshipCompanyService;
    @Autowired
    TrainingTeacherService trainingTeacherService;
    @Autowired
    SchoolTeacherService schoolTeacherService;
    @Autowired
    InternshipInfoService internshipInfoService;
    @Autowired
    CollegeService collegeService;

    //修改密码
    @GetMapping("/updateadminpassword")
    public String updatePassword(
            @Valid @RequestParam(value = "username") String username,
            @Valid @RequestParam(value = "password") String password,
            @Valid @RequestParam(value = "newpassword") String newpassword){
        AdminInfo adminInfo=new AdminInfo();
        Map<String,Object> map=new HashMap<>();
        map.put("password",password);
        map.put("username",username);
        //得到管理员id
        adminInfo=adminInfoService.getLogin(map);
        Map<String,Object> map1=new HashMap<>();
        map1.put("password",newpassword);
        map1.put("id",adminInfo.getId());
        if(adminInfoService.updateAP(map1)!=0){
            return "success";
        }else{
            return "error";
        }
    }

    //画图
    @GetMapping("/drawpicture")
    public List<AdminPicture> draw(){
        List<AdminPicture> list=new ArrayList<>();
        AdminPicture adminPicture=new AdminPicture();
        adminPicture.setId(1);
        adminPicture.setName("学生");
        adminPicture.setNum(studentInfoService.getnum());
        list.add(adminPicture);

        AdminPicture adminPicture1=new AdminPicture();
        adminPicture1.setId(2);
        adminPicture1.setName("学校教师");
        adminPicture1.setNum(schoolTeacherService.getnum());
        list.add(adminPicture1);

        AdminPicture adminPicture3=new AdminPicture();
        adminPicture3.setId(3);
        adminPicture3.setName("实训企业教师");
        adminPicture3.setNum(trainingTeacherService.getnum());
        list.add(adminPicture3);

        AdminPicture adminPicture4=new AdminPicture();
        adminPicture4.setId(4);
        adminPicture4.setName("实习公司负责人");
        adminPicture4.setNum(internshipCompanyService.getnum());
        list.add(adminPicture4);

        return list;
    }

    //每个学院的看到自己学院的薪资统计
    @GetMapping("/salary")
    public List<AdminPicture> Salary(@Valid @RequestParam(value = "username") String username){
        List<AdminPicture> list=new ArrayList<>();
        CollegeInfo collegeInfo=new CollegeInfo();
        if(username.length()==8 &&username.startsWith("1")) {//学生
            collegeInfo=collegeService.selectCollageByStuNum(username);
        } else if (username.length()==8 && !username.startsWith("1")) {//学校教师，长度8并且不以1开头
            collegeInfo=collegeService.selectCollageByStachNum(username);
        }else if (username.length()==11||username.endsWith(".com")) {//实训公司
            collegeInfo=collegeService.selectCollageByTrainNum(username);
        }
        ArrayList<InternshipInfo> internshipInfoArrayList=internshipInfoService.selectSalaryByCollageId(collegeInfo.getCoId());

        AdminPicture adminPicture1=new AdminPicture();
        adminPicture1.setId(1);
        adminPicture1.setName("2000以下");
        adminPicture1.setNum(0);

        AdminPicture adminPicture2=new AdminPicture();
        adminPicture2.setId(2);
        adminPicture2.setName("2000~3000");
        adminPicture2.setNum(0);

        AdminPicture adminPicture3=new AdminPicture();
        adminPicture3.setId(3);
        adminPicture3.setName("3000~4000");
        adminPicture3.setNum(0);

        AdminPicture adminPicture4=new AdminPicture();
        adminPicture4.setId(4);
        adminPicture4.setName("4000~5000");
        adminPicture4.setNum(0);

        AdminPicture adminPicture5=new AdminPicture();
        adminPicture5.setId(5);
        adminPicture5.setName("5000~6000");
        adminPicture5.setNum(0);

        AdminPicture adminPicture6=new AdminPicture();
        adminPicture6.setId(6);
        adminPicture6.setName("6000以上");
        adminPicture6.setNum(0);

        for(InternshipInfo i:internshipInfoArrayList){
            if(i.getOld_money()<2000){
                int temp= adminPicture1.getNum()+1;
                adminPicture1.setNum(temp);
            }else if(i.getOld_money()<3000 &&i.getOld_money()>=2000){
                int temp= adminPicture2.getNum()+1;
                adminPicture2.setNum(temp);
            }else if(i.getOld_money()<4000 &&i.getOld_money()>=3000){
                int temp= adminPicture3.getNum()+1;
                adminPicture3.setNum(temp);
            }else if(i.getOld_money()<5000 &&i.getOld_money()>=4000){
                int temp= adminPicture4.getNum()+1;
                adminPicture4.setNum(temp);
            }else if(i.getOld_money()<6000&&i.getOld_money()>=5000){
                int temp= adminPicture5.getNum()+1;
                adminPicture5.setNum(temp);
            }else if(i.getOld_money()>=6000){
                int temp= adminPicture6.getNum()+1;
                adminPicture6.setNum(temp);
            }
        }
        list.add(adminPicture1);
        list.add(adminPicture2);
        list.add(adminPicture3);
        list.add(adminPicture4);
        list.add(adminPicture5);
        list.add(adminPicture6);
        return list;
    }
    //每个学院的看到自己学院的地点统计
    @GetMapping("/location")
    public List<AdminPicture> location(@Valid @RequestParam(value = "username") String username){
        List<AdminPicture> list=new ArrayList<>();
        CollegeInfo collegeInfo=new CollegeInfo();
        if(username.length()==8 &&username.startsWith("1")) {//学生
            collegeInfo=collegeService.selectCollageByStuNum(username);
        } else if (username.length()==8 && !username.startsWith("1")) {//学校教师，长度8并且不以1开头
            collegeInfo=collegeService.selectCollageByStachNum(username);
        }else if (username.length()==11||username.endsWith(".com")) {//实训公司
            collegeInfo=collegeService.selectCollageByTrainNum(username);
        }
        ArrayList<InternshipInfo> internshipInfoArrayList=internshipInfoService.selectLocationByCollageId(collegeInfo.getCoId());
        int temopcount=1;
        for(InternshipInfo i:internshipInfoArrayList){
            AdminPicture adminPicture=new AdminPicture();
            adminPicture.setId(temopcount);
            temopcount++;
            adminPicture.setName(i.getAddress());
            adminPicture.setNum(i.getCount());
            list.add(adminPicture);
        }
        return list;
    }
}
