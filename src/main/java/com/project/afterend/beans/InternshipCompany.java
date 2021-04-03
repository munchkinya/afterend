package com.project.afterend.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InternshipCompany {
    private Integer interId;

    private String interName;

    private String interPassword;

    private String principal;

    private String interTel;

    private String address;
    private Integer delflag;

    private Integer roleid;

    private String vueStatus;

    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date starttime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endtime;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public Integer getInterId() {
        return interId;
    }

    public void setInterId(Integer interId) {
        this.interId = interId;
    }

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName == null ? null : interName.trim();
    }

    public String getInterPassword() {
        return interPassword;
    }

    public void setInterPassword(String interPassword) {
        this.interPassword = interPassword == null ? null : interPassword.trim();
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public String getInterTel() {
        return interTel;
    }

    public void setInterTel(String interTel) {
        this.interTel = interTel == null ? null : interTel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    @Override
    public String toString() {
        return "InternshipCompany{" +
                "interId=" + interId +
                ", interName='" + interName + '\'' +
                ", interPassword='" + interPassword + '\'' +
                ", principal='" + principal + '\'' +
                ", interTel='" + interTel + '\'' +
                ", address='" + address + '\'' +
                ", delflag=" + delflag +
                ", roleid=" + roleid +
                ", vueStatus='" + vueStatus + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}