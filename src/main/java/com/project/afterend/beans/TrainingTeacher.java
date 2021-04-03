package com.project.afterend.beans;

public class TrainingTeacher {
    private Integer tteachId;

    private String tteachNumber;

    private String tteachPassword;

    private String tteachHead;

    private String tteachName;

    private String tteachSex;

    private Integer trainId;

    private Integer dclassId;

    private Integer delflag;

    private Integer roleid;


    //多表查询

    private String traincompanyname;//所属公司

    private String collagename;//所在学院名称

    private String dclassname;//所教班级

    private String directname;//所教方向

    private Integer coid;//学院id

    private Integer diid;//方向id

    private String vueStatus;//前台

    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getVueStatus() {
        return vueStatus;
    }

    public void setVueStatus(String vueStatus) {
        this.vueStatus = vueStatus;
    }

    public Integer getDiid() {
        return diid;
    }

    public void setDiid(Integer diid) {
        this.diid = diid;
    }

    public Integer getCoid() {
        return coid;
    }

    public void setCoid(Integer coid) {
        this.coid = coid;
    }

    public String getTraincompanyname() {
        return traincompanyname;
    }

    public void setTraincompanyname(String traincompanyname) {
        this.traincompanyname = traincompanyname;
    }

    public String getCollagename() {
        return collagename;
    }

    public void setCollagename(String collagename) {
        this.collagename = collagename;
    }

    public String getDclassname() {
        return dclassname;
    }

    public void setDclassname(String dclassname) {
        this.dclassname = dclassname;
    }

    public String getDirectname() {
        return directname;
    }

    public void setDirectname(String directname) {
        this.directname = directname;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getTteachId() {
        return tteachId;
    }

    public void setTteachId(Integer tteachId) {
        this.tteachId = tteachId;
    }

    public String getTteachNumber() {
        return tteachNumber;
    }

    public void setTteachNumber(String tteachNumber) {
        this.tteachNumber = tteachNumber == null ? null : tteachNumber.trim();
    }

    public String getTteachPassword() {
        return tteachPassword;
    }

    public void setTteachPassword(String tteachPassword) {
        this.tteachPassword = tteachPassword == null ? null : tteachPassword.trim();
    }

    public String getTteachHead() {
        return tteachHead;
    }

    public void setTteachHead(String tteachHead) {
        this.tteachHead = tteachHead == null ? null : tteachHead.trim();
    }

    public String getTteachName() {
        return tteachName;
    }

    public void setTteachName(String tteachName) {
        this.tteachName = tteachName == null ? null : tteachName.trim();
    }

    public String getTteachSex() {
        return tteachSex;
    }

    public void setTteachSex(String tteachSex) {
        this.tteachSex = tteachSex == null ? null : tteachSex.trim();
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getDclassId() {
        return dclassId;
    }

    public void setDclassId(Integer dclassId) {
        this.dclassId = dclassId;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    @Override
    public String toString() {
        return "TrainingTeacher{" +
                "tteachId=" + tteachId +
                ", tteachNumber='" + tteachNumber + '\'' +
                ", tteachPassword='" + tteachPassword + '\'' +
                ", tteachHead='" + tteachHead + '\'' +
                ", tteachName='" + tteachName + '\'' +
                ", tteachSex='" + tteachSex + '\'' +
                ", trainId=" + trainId +
                ", dclassId=" + dclassId +
                ", delflag=" + delflag +
                ", roleid=" + roleid +
                ", traincompanyname='" + traincompanyname + '\'' +
                ", collagename='" + collagename + '\'' +
                ", dclassname='" + dclassname + '\'' +
                ", directname='" + directname + '\'' +
                '}';
    }
}