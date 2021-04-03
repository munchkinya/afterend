package com.project.afterend.beans;

import java.util.List;

public class RoleInfo {
    private Integer id;
    private String rolename;
    private String des;
    private List<MenuInfo> subMenuList;//为了在前台可伸展列下面直接显示子菜单

    public List<MenuInfo> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<MenuInfo> subMenuList) {
        this.subMenuList = subMenuList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", des='" + des + '\'' +
                ", subMenuList=" + subMenuList +
                '}';
    }
}
