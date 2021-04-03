package com.project.afterend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.afterend.beans.*;
import com.project.afterend.model.vo.trainteachervo.CollageAdd;
import com.project.afterend.model.vo.trainteachervo.DirectionClassAdd;
import com.project.afterend.model.vo.trainteachervo.DirectionInfoAdd;
import com.project.afterend.model.vo.trainteachervo.TrainingCompanyAdd;
import com.project.afterend.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/trainingteacher")
public class TrainingTeacherController {
    @Autowired
    TrainingTeacherService trainingTeacherService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    TrainingCompanyService trainingCompanyService;
    @Autowired
    DirectionInfoService directionInfoService;
    @Autowired
    DirectionClassService directionClassService;

    //查询学生全表，得到初始列表（最基本的表）
    @GetMapping(value = "/getalltrainteachers")
    public PageInfo<TrainingTeacher> getAll(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize, @Valid @RequestParam("query") String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<TrainingTeacher> list = trainingTeacherService.selectAll(query);
        PageInfo<TrainingTeacher> page = new PageInfo(list);
        return page;
    }
    /*修改一般发送put请求*/
    /*更新状态，0代表未删除，1代表删除*/
    @PutMapping(value = "/deletrainteach/{tteachId}/{delflag}")
    public TrainingTeacher delete(@Param("tteachId") @PathVariable(value = "tteachId") Integer tteachId, @PathVariable(value = "delflag") Integer delflag) {
        TrainingTeacher trainingTeacher=new TrainingTeacher();
        trainingTeacher.setDelflag(delflag);
        trainingTeacher.setTteachId(tteachId);
        trainingTeacherService.updatebydeflag(trainingTeacher);
        return trainingTeacherService.selectByPrimaryKey(tteachId);//这里只返回被修改的一条用户数据
    }

    //添加对话框里面的级联选择器
    @GetMapping(value = "/getallmessage")
    public List<CollageAdd> getall() {
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
    //添加功能
    @GetMapping(value = "/inserttrainteach")
    public String insert(@Valid @RequestParam("tteachNumber") String tteachNumber,
                         @Valid @RequestParam("tteachPassword") String tteachPassword,
                         @Valid @RequestParam("tteachName") String tteachName,
                         @Valid @RequestParam("tteachSex") String tteachSex,
                         @Valid @RequestParam("selectedOptions") String selectedOptions,
                         @Valid @RequestParam("roleid") String roleid) {
        String options[]=selectedOptions.split(",");//这里只需要培训公司id，和班级id，也就是第二个和第四个值
        if(trainingTeacherService.selectByUserName(tteachNumber)!=null){
            return "again";//用户名重复校验问题
        }
        TrainingTeacher trainingTeacher=new TrainingTeacher();
        trainingTeacher.setTrainId(Integer.parseInt(options[1]));
        trainingTeacher.setDclassId(Integer.parseInt(options[3]));
        trainingTeacher.setTteachNumber(tteachNumber);
        trainingTeacher.setTteachPassword(tteachPassword);
        trainingTeacher.setTteachName(tteachName);
        trainingTeacher.setTteachSex(tteachSex);
        if("实训企业普通教师".equals(roleid)){
            trainingTeacher.setRoleid(6);
        }else{
            trainingTeacher.setRoleid(5);
        }
        if(trainingTeacherService.insert(trainingTeacher)!=0){
            return "success";//返回成功的标志
        }
        return "fail";
    }
    //修改页面
    @GetMapping(value = "/getOnett")
    public TrainingTeacher insert(@Valid @RequestParam("tteachId") Integer tteachId) {
        return trainingTeacherService.selectByPrimaryKey(tteachId);
    }
    //修改教师信息（弹出修改页面的修改）
    @GetMapping(value = "/updatett")
    public TrainingTeacher update(@Valid @RequestParam("tteachId") Integer tteachId,
                                  @Valid @RequestParam("tteachNumber") String tteachNumber,
                                  @Valid @RequestParam("tteachPassword") String tteachPassword,
                                  @Valid @RequestParam("tteachName") String tteachName,
                                  @Valid @RequestParam("tteachSex") String tteachSex,
                                  @Valid @RequestParam("trainId") Integer trainId,
                                  @Valid @RequestParam("dclassId") Integer dclassId,
                                  @Valid @RequestParam("roleid") String roleid) {
        TrainingTeacher trainingTeacher=new TrainingTeacher();
        //先判断一下重复校验,防止没被修改
        if(trainingTeacherService.selectByUserName(tteachNumber)!=null){
            if(trainingTeacherService.selectByUserName(tteachNumber).getTteachId()!=tteachId){
                trainingTeacher.setVueStatus("again");
                return trainingTeacher;
            }
        }
        trainingTeacher.setTteachId(tteachId);
        trainingTeacher.setTteachNumber(tteachNumber);
        trainingTeacher.setTteachPassword(tteachPassword);
        trainingTeacher.setTteachName(tteachName);
        trainingTeacher.setTteachSex(tteachSex);
        trainingTeacher.setTrainId(trainId);
        trainingTeacher.setDclassId(dclassId);
        if("实训企业普通教师".equals(roleid)){
            trainingTeacher.setRoleid(6);
        }else{
            trainingTeacher.setRoleid(5);
        }
        trainingTeacherService.updateByPrimaryKey(trainingTeacher);
        return trainingTeacherService.selectByPrimaryKey(tteachId);//这里只返回被修改的一条用户数据
    }

    //给教师分配新的角色
    @PutMapping(value = "/updatettrole/{tteachId}/{roleid}")
    public TrainingTeacher updatestrole(@Param("tteachId") @PathVariable(value = "tteachId") Integer tteachId, @PathVariable(value = "roleid") Integer roleid) {
        TrainingTeacher trainingTeacher=new TrainingTeacher();
        trainingTeacher.setTteachId(tteachId);
        trainingTeacher.setRoleid(roleid);
        trainingTeacherService.updaterole(trainingTeacher);
        return trainingTeacherService.selectByPrimaryKey(tteachId);
    }

}
