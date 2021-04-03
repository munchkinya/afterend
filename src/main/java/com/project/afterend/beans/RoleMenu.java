package com.project.afterend.beans;

public class RoleMenu {
    private Integer id;
    private Integer roleid;
    private Integer menuid;

    @Override
    public String toString() {
        return "RoleMenu{" +
                "id=" + id +
                ", roleid=" + roleid +
                ", menuid=" + menuid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }
}
