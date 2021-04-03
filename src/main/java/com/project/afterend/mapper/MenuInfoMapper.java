package com.project.afterend.mapper;

import com.project.afterend.beans.MenuInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuInfoMapper {
    public List<MenuInfo> getAllMenu();//得到所有权限列表
    public List<MenuInfo> getMenuList(Map<Object, Object> map);//根据 parentID 和 roleID 查询 所有菜单（一级和二级）
    public List<MenuInfo> getAllMenuList(int pid);//根据父级id查询子菜单
    public List<MenuInfo> getAllMenuTree();//封装树形结构
    public List<MenuInfo> selectMenuByPId(int pid);//根据id找菜单
}
