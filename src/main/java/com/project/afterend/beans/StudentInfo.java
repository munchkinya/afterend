package com.project.afterend.beans;

public class StudentInfo {
    private Integer stuId;

    private String stuNumber;

    private String stuPassword;

    private String stuHead;

    private String stuName;

    private String stuSex;

    private String stuEmail;

    private String stuTel;

    private Integer adId;

    private Integer dclassId;

    private Integer delflag;

    private Integer roleid;

    private Integer tteachid;

    private Integer steachid;

    private Integer grade;

    //下面全部是多表查询
    private String adminclassname;//行政专业班级
    private Integer majorId;//专业id
    private String majorname;//专业名称
    private Integer collegeId;//院系id
    private String collagename;//院系名称
    private String directclassname;//方向班级
    private Integer directinfo;//方向id
    private String directname;//方向名称
    private String trainteachername;//校外指导教师名字
    private Integer traincomid;//实训公司id
    private String traincomname;//实训公司名称
    private String schoolteachername;//校内指导教师名称

    private String VueStatus;//为了前台判断条件

    public String getVueStatus() {
        return VueStatus;
    }

    public void setVueStatus(String vueStatus) {
        VueStatus = vueStatus;
    }

    public Integer getSteachid() {
        return steachid;
    }

    public void setSteachid(Integer steachid) {
        this.steachid = steachid;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public String getStuHead() {
        return stuHead;
    }

    public void setStuHead(String stuHead) {
        this.stuHead = stuHead;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuTel() {
        return stuTel;
    }

    public void setStuTel(String stuTel) {
        this.stuTel = stuTel;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
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

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getTteachid() {
        return tteachid;
    }

    public void setTteachid(Integer tteachid) {
        this.tteachid = tteachid;
    }

    public String getAdminclassname() {
        return adminclassname;
    }

    public void setAdminclassname(String adminclassname) {
        this.adminclassname = adminclassname;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollagename() {
        return collagename;
    }

    public void setCollagename(String collagename) {
        this.collagename = collagename;
    }

    public String getDirectclassname() {
        return directclassname;
    }

    public void setDirectclassname(String directclassname) {
        this.directclassname = directclassname;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getDirectinfo() {
        return directinfo;
    }

    public void setDirectinfo(Integer directinfo) {
        this.directinfo = directinfo;
    }

    public String getDirectname() {
        return directname;
    }

    public void setDirectname(String directname) {
        this.directname = directname;
    }

    public String getTrainteachername() {
        return trainteachername;
    }

    public void setTrainteachername(String trainteachername) {
        this.trainteachername = trainteachername;
    }

    public Integer getTraincomid() {
        return traincomid;
    }

    public void setTraincomid(Integer traincomid) {
        this.traincomid = traincomid;
    }

    public String getTraincomname() {
        return traincomname;
    }

    public void setTraincomname(String traincomname) {
        this.traincomname = traincomname;
    }

    public String getSchoolteachername() {
        return schoolteachername;
    }

    public void setSchoolteachername(String schoolteachername) {
        this.schoolteachername = schoolteachername;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "stuId=" + stuId +
                ", stuNumber='" + stuNumber + '\'' +
                ", stuPassword='" + stuPassword + '\'' +
                ", stuHead='" + stuHead + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuEmail='" + stuEmail + '\'' +
                ", stuTel='" + stuTel + '\'' +
                ", adId=" + adId +
                ", dclassId=" + dclassId +
                ", delflag=" + delflag +
                ", roleid=" + roleid +
                ", tteachid=" + tteachid +
                ", steachid=" + steachid +
                ", adminclassname='" + adminclassname + '\'' +
                ", majorId=" + majorId +
                ", majorname='" + majorname + '\'' +
                ", collegeId=" + collegeId +
                ", collagename='" + collagename + '\'' +
                ", directclassname='" + directclassname + '\'' +
                ", grade=" + grade +
                ", directinfo=" + directinfo +
                ", directname='" + directname + '\'' +
                ", trainteachername='" + trainteachername + '\'' +
                ", traincomid=" + traincomid +
                ", traincomname='" + traincomname + '\'' +
                ", schoolteachername='" + schoolteachername + '\'' +
                '}';
    }
}