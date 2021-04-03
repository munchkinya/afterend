package com.project.afterend.service;

import com.project.afterend.beans.MenuInfo;
import com.project.afterend.mapper.MenuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuInfoService {
    @Autowired
    MenuInfoMapper menuInfoMapper;
    //查全表
    public List<MenuInfo> getAllMenu(){
        return menuInfoMapper.getAllMenu();
    }
    //根据 parentID 和 roleID 查询 所有菜单（一级和二级）
    public List<MenuInfo> getMenuList(Map<Object, Object> map){
        return menuInfoMapper.getMenuList(map);
    }
    //根据父级id查询子菜单
    public List<MenuInfo> getAllMenuList(int pid){
        return menuInfoMapper.getAllMenuList(pid);
    }
    //封装树形结构
    public List<MenuInfo> getAllMenuTree(){
        return menuInfoMapper.getAllMenuTree();
    }
    //根据id找菜单
    public List<MenuInfo> selectMenuByPId(int pid){
        return menuInfoMapper.selectMenuByPId(pid);
    }
}
