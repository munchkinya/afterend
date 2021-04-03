package com.project.afterend.beans;

public class SchoolTeacher {
    private Integer steachId;

    private String steachNumber;

    private String steachPassword;

    private String steachName;

    private String steachSex;

    private String steachEmail;

    private String steachTel;

    private Integer coId;

    private String coname;//院系名称，为了多表查询

    private String steachHead;

    private Integer delflag;

    private Integer roleid;//角色id

    private String rolename;//角色名称，为了多表查询

    private String vueStatus;

    public String getVueStatus() {
        return vueStatus;
    }

    public void setVueStatus(String vueStatus) {
        this.vueStatus = vueStatus;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }


    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        this.coname = coname;
    }

    public Integer getSteachId() {
        return steachId;
    }

    public void setSteachId(Integer steachId) {
        this.steachId = steachId;
    }

    public String getSteachNumber() {
        return steachNumber;
    }

    public void setSteachNumber(String steachNumber) {
        this.steachNumber = steachNumber == null ? null : steachNumber.trim();
    }

    public String getSteachPassword() {
        return steachPassword;
    }

    public void setSteachPassword(String steachPassword) {
        this.steachPassword = steachPassword == null ? null : steachPassword.trim();
    }

    public String getSteachName() {
        return steachName;
    }

    public void setSteachName(String steachName) {
        this.steachName = steachName == null ? null : steachName.trim();
    }

    public String getSteachSex() {
        return steachSex;
    }

    public void setSteachSex(String steachSex) {
        this.steachSex = steachSex == null ? null : steachSex.trim();
    }

    public String getSteachEmail() {
        return steachEmail;
    }

    public void setSteachEmail(String steachEmail) {
        this.steachEmail = steachEmail == null ? null : steachEmail.trim();
    }

    public String getSteachTel() {
        return steachTel;
    }

    public void setSteachTel(String steachTel) {
        this.steachTel = steachTel == null ? null : steachTel.trim();
    }

    public Integer getCoId() {
        return coId;
    }

    public void setCoId(Integer coId) {
        this.coId = coId;
    }

    public String getSteachHead() {
        return steachHead;
    }

    public void setSteachHead(String steachHead) {
        this.steachHead = steachHead == null ? null : steachHead.trim();
    }

    @Override
    public String toString() {
        return "SchoolTeacher{" +
                "steachId=" + steachId +
                ", steachNumber='" + steachNumber + '\'' +
                ", steachPassword='" + steachPassword + '\'' +
                ", steachName='" + steachName + '\'' +
                ", steachSex='" + steachSex + '\'' +
                ", steachEmail='" + steachEmail + '\'' +
                ", steachTel='" + steachTel + '\'' +
                ", coId=" + coId +
                ", coname='" + coname + '\'' +
                ", steachHead='" + steachHead + '\'' +
                ", delflag=" + delflag +
                ", roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}