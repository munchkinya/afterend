package com.project.afterend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.afterend.beans.InternshipCompany;
import com.project.afterend.beans.TrainingCompany;
import com.project.afterend.service.InternshipCompanyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/internshipcompany")
public class InternshipCompanyController {
    @Autowired
    InternshipCompanyService internshipCompanyService;

    //查询全表，得到初始列表（最基本的表）
    @GetMapping(value = "/getallintercoms")
    public PageInfo<InternshipCompany> getAll(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize, @Valid @RequestParam("query") String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<InternshipCompany> list = internshipCompanyService.selectAll(query);
        /*这里是为了保证实习公司表中的status是最新的状态，所以每次得到简历时，都必须刷新*/
        for (InternshipCompany l:list){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            /*System.out.println(formatter.format(date));*/
            /*System.out.println(l.getStarttime());*/
        }
        PageInfo<InternshipCompany> page = new PageInfo(list);
        return page;
    }
    /*修改一般发送put请求*/
    /*更新状态，0代表未删除，1代表删除*/
    @PutMapping(value = "/deleintercom/{interId}/{delflag}")
    public InternshipCompany delete(@Param("interId") @PathVariable(value = "interId") Integer interId, @PathVariable(value = "delflag") Integer delflag) {
        InternshipCompany internshipCompany=new InternshipCompany();
        internshipCompany.setDelflag(delflag);
        internshipCompany.setInterId(interId);
        internshipCompanyService.updatebydeflag(internshipCompany);
        return internshipCompanyService.selectByPrimaryKey(interId);//这里只返回被修改的一条用户数据
    }
    //添加功能
    @GetMapping(value = "/insertinterncom")
    public String insert(@Valid @RequestParam("interName") String interName,
                         @Valid @RequestParam("principal") String principal,
                         @Valid @RequestParam("interTel") String interTel,
                         @Valid @RequestParam("address") String address,
                         @Valid @RequestParam("interPassword") String interPassword) {
        if(internshipCompanyService.selectByUserName(interName)!=null){
            return "again";//用户名重复校验问题
        }
        InternshipCompany internshipCompany=new InternshipCompany();
        internshipCompany.setInterName(interName);
        internshipCompany.setInterPassword(interPassword);
        internshipCompany.setPrincipal(principal);
        internshipCompany.setInterTel(interTel);
        internshipCompany.setAddress(address);
        if(internshipCompanyService.insert(internshipCompany)!=0){
            return "success";//返回成功的标志
        }
        return "fail";
    }
    //修改页面
    @GetMapping(value = "/getoneIC")
    public InternshipCompany insert(@Valid @RequestParam("interId") Integer interId) {
        return internshipCompanyService.selectByPrimaryKey(interId);
    }

    //修改信息（弹出修改页面的修改）
    @GetMapping(value = "/updateic")
    public InternshipCompany update(@Valid @RequestParam("interId") Integer interId,
                                  @Valid @RequestParam("interName") String interName,
                                  @Valid @RequestParam("interPassword") String interPassword,
                                  @Valid @RequestParam("principal") String principal,
                                  @Valid @RequestParam("interTel") String interTel,
                                  @Valid @RequestParam("address") String address) {
        InternshipCompany internshipCompany=new InternshipCompany();
        //先判断一下重复校验,防止没被修改
        if(internshipCompanyService.selectByUserName(interName)!=null){
            if(internshipCompanyService.selectByUserName(interName).getInterId()!=interId){
                internshipCompany.setVueStatus("again");
                return internshipCompany;
            }
        }
        internshipCompany.setInterId(interId);
        internshipCompany.setInterName(interName);
        internshipCompany.setInterPassword(interPassword);
        internshipCompany.setPrincipal(principal);
        internshipCompany.setInterTel(interTel);
        internshipCompany.setAddress(address);
        internshipCompanyService.updateByPrimaryKey(internshipCompany);
        return internshipCompanyService.selectByPrimaryKey(interId);
    }

    //得到所有正在招聘的公司
    @GetMapping(value = "/getintercompanys")
    public List<InternshipCompany> getAll() {
        List<InternshipCompany> list = internshipCompanyService.selectInternshipcompany();
        return list;
    }




}
