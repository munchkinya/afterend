package com.project.afterend.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InternshipInfo {
    private Integer inId;

    private Integer stuId;

    private Integer interId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date starttime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date new_starttime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date new_endtime;

    private Integer delflag;

    private String status;

    private String vueStatus;
    private String new_address;
    private String text;

    private String old_post;
    private Double old_money;
    private String new_post;
    private Double new_money;
    private String new_company;
    private String address;
    private String signall;
    private Integer count;//为了画图
    /*0：正常1；提交了审核2.审核结束*/
    private Integer action;
    private String intershipscoreone;
    private String intershipscoretwo;
    private String finalintershipscore;


    public String getVueStatus() {
        return vueStatus;
    }

    public void setVueStatus(String vueStatus) {
        this.vueStatus = vueStatus;
    }

    //下面是多表查询

    private String stunumber;
    private String stuname;
    private String intername;
    private String stusex;
    private String majname;
    private String adname;
    private Integer coid;
    private String coname;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }



    public String getSignall() {
        return signall;
    }

    public void setSignall(String signall) {
        this.signall = signall;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNew_address() {
        return new_address;
    }

    public void setNew_address(String new_address) {
        this.new_address = new_address;
    }

    public Date getNew_starttime() {
        return new_starttime;
    }

    public void setNew_starttime(Date new_starttime) {
        this.new_starttime = new_starttime;
    }

    public Date getNew_endtime() {
        return new_endtime;
    }

    public void setNew_endtime(Date new_endtime) {
        this.new_endtime = new_endtime;
    }

    public String getNew_company() {
        return new_company;
    }

    public void setNew_company(String new_company) {
        this.new_company = new_company;
    }

    public String getOld_post() {
        return old_post;
    }

    public void setOld_post(String old_post) {
        this.old_post = old_post;
    }

    public Double getOld_money() {
        return old_money;
    }

    public void setOld_money(Double old_money) {
        this.old_money = old_money;
    }

    public String getNew_post() {
        return new_post;
    }

    public void setNew_post(String new_post) {
        this.new_post = new_post;
    }

    public Double getNew_money() {
        return new_money;
    }

    public void setNew_money(Double new_money) {
        this.new_money = new_money;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStunumber() {
        return stunumber;
    }

    public void setStunumber(String stunumber) {
        this.stunumber = stunumber;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getIntername() {
        return intername;
    }

    public void setIntername(String intername) {
        this.intername = intername;
    }

    public Integer getCoid() {
        return coid;
    }

    public void setCoid(Integer coid) {
        this.coid = coid;
    }

    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        this.coname = coname;
    }

    public Integer getInId() {
        return inId;
    }

    public void setInId(Integer inId) {
        this.inId = inId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getInterId() {
        return interId;
    }

    public void setInterId(Integer interId) {
        this.interId = interId;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public String getStusex() {
        return stusex;
    }

    public void setStusex(String stusex) {
        this.stusex = stusex;
    }

    public String getMajname() {
        return majname;
    }

    public void setMajname(String majname) {
        this.majname = majname;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public String getIntershipscoreone() {
        return intershipscoreone;
    }

    public void setIntershipscoreone(String intershipscoreone) {
        this.intershipscoreone = intershipscoreone;
    }

    public String getIntershipscoretwo() {
        return intershipscoretwo;
    }

    public void setIntershipscoretwo(String intershipscoretwo) {
        this.intershipscoretwo = intershipscoretwo;
    }

    public String getFinalintershipscore() {
        return finalintershipscore;
    }

    public void setFinalintershipscore(String finalintershipscore) {
        this.finalintershipscore = finalintershipscore;
    }

    @Override
    public String toString() {
        return "InternshipInfo{" +
                "inId=" + inId +
                ", stuId=" + stuId +
                ", interId=" + interId +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", new_starttime=" + new_starttime +
                ", new_endtime=" + new_endtime +
                ", delflag=" + delflag +
                ", status='" + status + '\'' +
                ", vueStatus='" + vueStatus + '\'' +
                ", new_address='" + new_address + '\'' +
                ", text='" + text + '\'' +
                ", old_post='" + old_post + '\'' +
                ", old_money=" + old_money +
                ", new_post='" + new_post + '\'' +
                ", new_money=" + new_money +
                ", new_company='" + new_company + '\'' +
                ", address='" + address + '\'' +
                ", signall='" + signall + '\'' +
                ", count=" + count +
                ", action=" + action +
                ", intershipscoreone='" + intershipscoreone + '\'' +
                ", intershipscoretwo='" + intershipscoretwo + '\'' +
                ", finalintershipscore='" + finalintershipscore + '\'' +
                ", stunumber='" + stunumber + '\'' +
                ", stuname='" + stuname + '\'' +
                ", intername='" + intername + '\'' +
                ", stusex='" + stusex + '\'' +
                ", majname='" + majname + '\'' +
                ", adname='" + adname + '\'' +
                ", coid=" + coid +
                ", coname='" + coname + '\'' +
                '}';
    }
}