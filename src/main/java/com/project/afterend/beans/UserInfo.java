package com.project.afterend.beans;
//为了判断登陆专门设的一个类，数据库里面并不存在这个表,是因为我把JWT，token值写死了
public class UserInfo {
    private Integer id;
    private String adminname;//这里没设username是因为我前台写好了，改有点麻烦
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", adminname='" + adminname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
