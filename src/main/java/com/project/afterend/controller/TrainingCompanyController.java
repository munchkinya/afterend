package com.project.afterend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.afterend.beans.*;
import com.project.afterend.service.TrainingCompanyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trainingcompany")
public class TrainingCompanyController {
    @Autowired
    TrainingCompanyService trainingCompanyService;

    //查询学生全表，得到初始列表（最基本的表）
    @GetMapping(value = "/getalltraincoms")
    public PageInfo<TrainingCompany> getAll(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize, @Valid @RequestParam("query") String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<TrainingCompany> list = trainingCompanyService.selectAll(query);
        PageInfo<TrainingCompany> page = new PageInfo(list);
        return page;
    }
    /*修改一般发送put请求*/
    /*更新状态，0代表未删除，1代表删除*/
    @PutMapping(value = "/deletraincom/{trainId}/{delflag}")
    public TrainingCompany delete(@Param("trainId") @PathVariable(value = "trainId") Integer trainId, @PathVariable(value = "delflag") Integer delflag) {
        TrainingCompany trainingCompany=new TrainingCompany();
        trainingCompany.setDelflag(delflag);
        trainingCompany.setTrainId(trainId);
        trainingCompanyService.updatebydeflag(trainingCompany);
        return trainingCompanyService.selectByPrimaryKey(trainId);//这里只返回被修改的一条用户数据
    }
    //添加功能
    @GetMapping(value = "/inserttraincom")
    public String insert(@Valid @RequestParam("trainName") String trainName,@Valid @RequestParam("principal") String principal,@Valid @RequestParam("trainTel") String trainTel,
                         @Valid @RequestParam("collagename") String collagename) {
        if(trainingCompanyService.selectByUserName(trainName)!=null){
            return "again";//用户名重复校验问题
        }
        TrainingCompany trainingCompany=new TrainingCompany();
        trainingCompany.setTrainName(trainName);
        trainingCompany.setPrincipal(principal);
        trainingCompany.setTrainTel(trainTel);
        trainingCompany.setCollageId(Integer.parseInt(collagename));//实际传递的事学院的id，而不是名称
        if(trainingCompanyService.insert(trainingCompany)!=0){
            return "success";//返回成功的标志
        }
        return "fail";
    }

    //修改页面
    @GetMapping(value = "/getOneTC")
    public TrainingCompany insert(@Valid @RequestParam("trainId") Integer trainId) {
        return trainingCompanyService.selectByPrimaryKey(trainId);
    }

    //修改公司信息（弹出修改页面的修改）
    @GetMapping(value = "/updatetc")
    public TrainingCompany update(@Valid @RequestParam("trainId") Integer trainId,
                                  @Valid @RequestParam("trainName") String trainName,
                                  @Valid @RequestParam("collageId") Integer collageId,
                                  @Valid @RequestParam("principal") String principal,
                                  @Valid @RequestParam("trainTel") String trainTel
    ) {
        TrainingCompany trainingCompany=new TrainingCompany();
        //先判断一下重复校验,防止没被修改
        if(trainingCompanyService.selectByUserName(trainName)!=null){
            if(trainingCompanyService.selectByUserName(trainName).getTrainId()!=trainId){
                trainingCompany.setVueStatus("again");
                return trainingCompany;
            }
        }
        trainingCompany.setTrainName(trainName);
        trainingCompany.setTrainTel(trainTel);
        trainingCompany.setPrincipal(principal);
        trainingCompany.setCollageId(collageId);
        trainingCompany.setTrainId(trainId);
        trainingCompanyService.updateByPrimaryKey(trainingCompany);
        return trainingCompanyService.selectByPrimaryKey(trainId);//这里只返回被修改的一条用户数据
    }

}
