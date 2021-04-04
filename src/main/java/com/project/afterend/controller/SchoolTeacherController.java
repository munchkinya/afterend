package com.project.afterend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.afterend.beans.CollegeInfo;
import com.project.afterend.beans.SchoolTeacher;
import com.project.afterend.beans.StudentInfo;
import com.project.afterend.beans.TrainingTeacher;
import com.project.afterend.service.CollegeService;
import com.project.afterend.service.SchoolTeacherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SchoolTeacherController {
    @Autowired
    SchoolTeacherService schoolTeacherService;
    @Autowired
    CollegeService collegeService;

    //查询教师全表，得到教师初始列表（最基本的表）
    @GetMapping(value = "/getAllSTs")
    public PageInfo<SchoolTeacher> getAll(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize, @Valid @RequestParam("query") String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<SchoolTeacher> list = schoolTeacherService.getAll(query);
        PageInfo<SchoolTeacher> page = new PageInfo(list);
        return page;
    }

    /*修改一般发送put请求*/
    /*更新教师状态，0代表未删除，1代表删除*/
    @PutMapping(value = "/delesteach/{steachId}/{delflag}")
    public SchoolTeacher delete(@Param("steachId") @PathVariable(value = "steachId") Integer steachId, @PathVariable(value = "delflag") Integer delflag) {
        SchoolTeacher schoolTeacher = new SchoolTeacher();
        schoolTeacher.setSteachId(steachId);
        schoolTeacher.setDelflag(delflag);
        schoolTeacherService.delete(schoolTeacher);
        return schoolTeacherService.selectByPrimaryKey(steachId);//这里只返回被修改的一条用户数据
    }

    //查询全部学院信息(添加用户时显示)
    @GetMapping(value = "/getallcollage")
    public List<CollegeInfo> getAll() {
        return collegeService.getAllCollage();
    }

    //添加教师
    @GetMapping(value = "/insertst")
    public String insert(@Valid @RequestParam("steachNumber") String steachNumber,
                         @Valid @RequestParam("steachPassword") String steachPassword,
                         @Valid @RequestParam("steachName") String steachName,
                         @Valid @RequestParam("steachSex") String steachSex,
                         @Valid @RequestParam("steachEmail") String steachEmail,
                         @Valid @RequestParam("steachTel") String steachTel,
                         @Valid @RequestParam("coname") String coname,
                         @Valid @RequestParam("roleid") String roleid) {
        if(schoolTeacherService.selectBysteachNumber(steachNumber)!=null){
            return "again";//用户名重复校验问题
        }
        SchoolTeacher schoolTeacher=new SchoolTeacher();
        schoolTeacher.setSteachNumber(steachNumber);
        schoolTeacher.setSteachPassword(steachPassword);
        schoolTeacher.setSteachName(steachName);
       schoolTeacher.setSteachSex(steachSex);
       schoolTeacher.setSteachEmail(steachEmail);
       schoolTeacher.setSteachTel(steachTel);
       schoolTeacher.setCoId(Integer.parseInt(coname));
        if("学院普通教师".equals(roleid)){
            schoolTeacher.setRoleid(4);
        }else{
            schoolTeacher.setRoleid(3);
        }
        if(schoolTeacherService.insert(schoolTeacher)!=0){
            return "success";//返回成功的标志
        }
        return "fail";
    }

    //根据id查教师
    @GetMapping(value = "/getTs")
    public SchoolTeacher getOneSchoolTeacher(@Valid @RequestParam("id") Integer id) {
        SchoolTeacher schoolTeacher=new SchoolTeacher();
        schoolTeacher=schoolTeacherService.selectByPrimaryKey(id);
        return schoolTeacher;
    }

    //修改教师信息（弹出修改页面的修改）
    @PutMapping(value = "/updatesteach/{steachId}/{steachNumber}/{steachPassword}/{steachName}/{steachEmail}/{coId}/{steachSex}/{steachTel}")
    public SchoolTeacher update(@Param("steachId") @PathVariable(value = "steachId") Integer steachId,@PathVariable(value = "steachNumber") String steachNumber,
                                @PathVariable(value = "steachPassword") String steachPassword,@PathVariable(value = "coId") Integer coId,
                                @PathVariable(value = "steachName") String steachName,@PathVariable(value = "steachSex") String steachSex,
                                @PathVariable(value = "steachEmail") String steachEmail,@PathVariable(value = "steachTel") String steachTel
                                ) {
        SchoolTeacher schoolTeacher = new SchoolTeacher();
        //先判断一下职工号重复校验,防止职工号没被修改
        if(schoolTeacherService.selectBysteachNumber(steachNumber)!=null){
            if(schoolTeacherService.selectBysteachNumber(steachNumber).getSteachId()!=steachId){
                schoolTeacher.setVueStatus("again");
                return schoolTeacher;
            }
        }
        schoolTeacher.setSteachId(steachId);
        schoolTeacher.setSteachPassword(steachPassword);
        schoolTeacher.setSteachNumber(steachNumber);
        schoolTeacher.setCoId(coId);
        schoolTeacher.setSteachName(steachName);
        schoolTeacher.setSteachSex(steachSex);
        schoolTeacher.setSteachEmail(steachEmail);
        schoolTeacher.setSteachTel(steachTel);
        schoolTeacherService.updateByPrimaryKeySelective(schoolTeacher);
        return schoolTeacherService.selectByPrimaryKey(steachId);//这里只返回被修改的一条用户数据
    }
    //给教师分配新的角色
    @PutMapping(value = "/updatestrole/{steachId}/{roleid}")
    public SchoolTeacher updatestrole(@Param("steachId") @PathVariable(value = "steachId") Integer steachId, @PathVariable(value = "roleid") Integer roleid) {
        SchoolTeacher schoolTeacher=new SchoolTeacher();
        schoolTeacher.setRoleid(roleid);
        schoolTeacher.setSteachId(steachId);
        schoolTeacherService.upfatestrole(schoolTeacher);
        return schoolTeacherService.selectByPrimaryKey(steachId);//这里只返回被修改的一条用户数据
    }
    //上传excel，批量导入
    @RequestMapping(value = "/excel",method = RequestMethod.POST)
    void getAllByExcel(@RequestParam("file") MultipartFile MPfile){

    }
    File transfer(MultipartFile file) {
        if (file != null) {
            try {
                String filePath = "C:\\Users\\JAJ5SZH\\IdeaProjects\\Will-Backend\\UploadFile\\"+file.getOriginalFilename();
                File savedFile = new File(filePath);
                boolean isCreateSuccess = savedFile.createNewFile();
                // 是否创建文件成功
                if (isCreateSuccess) {
                    //将文件写入
                    file.transferTo(savedFile);
                    return savedFile;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件是空的");
        }
        return null;
    }
}