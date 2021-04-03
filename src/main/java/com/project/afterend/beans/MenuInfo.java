package com.project.afterend.beans;

import java.util.List;

public class MenuInfo {
    private Integer id;
    private String menuname;
    private Integer pid;
    private String path;
    private Integer level;
    private List<MenuInfo> subMenuList;//表示当前菜单的子菜单

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

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "MenuInfo{" +
                "id=" + id +
                ", menuname='" + menuname + '\'' +
                ", pid=" + pid +
                ", path='" + path + '\'' +
                ", level=" + level +
                ", subMenuList=" + subMenuList +
                '}';
    }
}
