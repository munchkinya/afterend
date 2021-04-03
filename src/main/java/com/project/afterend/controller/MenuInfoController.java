package com.project.afterend.controller;

import com.project.afterend.beans.MenuInfo;
import com.project.afterend.beans.RoleInfo;
import com.project.afterend.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuInfoController {
    @Autowired
    MenuInfoService menuInfoService;

    //得到所有角色
    @GetMapping(value = "/getallmenu")
    public List<MenuInfo> getAll() {
        return menuInfoService.getAllMenu();
    }

    //分配权限（封装树状图）
    @Transactional
    @GetMapping(value = "/getallmenutree")
    public List<MenuInfo> selectTree(){
        List<MenuInfo> menuInfoList=menuInfoService.getAllMenuTree();
        List<MenuInfo> result=new ArrayList<>();
        for(MenuInfo menuInfo:menuInfoList){
            if(menuInfo.getId()!=0){
                MenuInfo menuInfo1=getTree(menuInfo);
                if(menuInfo1!=null){
                    result.add(menuInfo1);
                }
            }
        }
        return result;
    }
    //和上面那个一样是封装类
    public MenuInfo getTree(MenuInfo menuInfo){
        List<MenuInfo> menuInfoList=menuInfoService.selectMenuByPId(menuInfo.getId());
        if(menuInfoList!=null){
            menuInfo.setSubMenuList(menuInfoList);
            for(MenuInfo list:menuInfoList){
                if(list.getId()!=0){
                    getTree(list);
                }
            }
        }
        return menuInfo;
    }
}
