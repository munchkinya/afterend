package com.project.afterend.controller;

import com.project.afterend.beans.*;
import com.project.afterend.model.ApiResponse;
import com.project.afterend.model.ApiResponseEnum;
import com.project.afterend.service.*;
import com.project.afterend.util.ApiResponseUtil;
import com.project.afterend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    AdminInfoService adminInfoService;//就只给了admin,也就只有这个账号
    @Autowired
    StudentInfoService studentInfoService;//学号
    @Autowired
    SchoolTeacherService schoolTeacherService;//职工号
    @Autowired
    TrainingTeacherService trainingTeacherService;//电话号或者邮箱
    @Autowired
    InternshipCompanyService internshipCompanyService;//公司名称

    @GetMapping("/login")
    public ApiResponse login(@Valid @RequestParam("username") String username,@Valid @RequestParam("password") String password) throws UnsupportedEncodingException {
        UserInfo isSuccess=new UserInfo();
        Map<String,Object> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        //身份验证是否成功
        out:if(username.length()==8 &&username.startsWith("1")){//学生
            map.put("username",Integer.parseInt(username));
            map.put("password",Integer.parseInt(password));//这里不知道为什么一定要转换成数字，要不然就一直报空
            StudentInfo studentInfo=studentInfoService.getLogin(map);
            if(studentInfo!=null){
                isSuccess.setId(studentInfo.getStuId());
                isSuccess.setAdminname(studentInfo.getStuNumber());
                isSuccess.setPassword(studentInfo.getStuPassword());
            }
        }
        else if (username.length()==8 && !username.startsWith("1")){//学校教师，长度8并且不以1开头
            SchoolTeacher schoolTeacher=schoolTeacherService.getLogin(map);
            if(schoolTeacher!=null){
                isSuccess.setId(schoolTeacher.getSteachId());
                isSuccess.setAdminname(schoolTeacher.getSteachNumber());
                isSuccess.setPassword(schoolTeacher.getSteachPassword());
            }
        }else if (username.length()==11||username.endsWith(".com")){//实训公司
            TrainingTeacher trainingTeacher=trainingTeacherService.getLogin(map);
            if(trainingTeacher!=null){
                isSuccess.setId(trainingTeacher.getTteachId());
                isSuccess.setAdminname(trainingTeacher.getTteachNumber());
                isSuccess.setPassword(trainingTeacher.getTteachPassword());
            }
        }else if(username.startsWith("admin")){//管理员
            AdminInfo adminInfo=adminInfoService.getLogin(map);
            if(adminInfo!=null){
                isSuccess.setId(adminInfo.getId());
                isSuccess.setAdminname(adminInfo.getAdminname());
                isSuccess.setPassword(adminInfo.getPassword());
            }
        }else{//还剩两种情况：没账号和实习公司
            InternshipCompany internshipCompany=internshipCompanyService.getLogin(map);
            if(internshipCompany==null){
                //根本不存在这个账号
                //返回登陆失败消息
                break out;
            }else{
                isSuccess.setId(internshipCompany.getInterId());
                isSuccess.setAdminname(internshipCompany.getInterName());
                isSuccess.setPassword(internshipCompany.getInterPassword());
            }
        }
        //这里有个bug，还不能直接用对象为nul，因为上面new了对象，对象不为空
        if (isSuccess.getId()!=null) {//身份验证成功
            //返回token.
            String id=isSuccess.getId()+"";
            String token = JwtUtil.sign(isSuccess.getAdminname(), id);
            if (token != null) {
                return ApiResponseUtil.getApiResponse(token);
            }
        }
        //返回登陆失败消息
        return ApiResponseUtil.getApiResponse(ApiResponseEnum.LOGIN_FAIL);
    }
}
