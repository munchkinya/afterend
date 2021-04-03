package com.project.afterend.beans;

import javax.xml.crypto.Data;

public class ResumeInfo {
    private Integer id;
    private Integer stu_id;
    private Integer intercom_id;
    private String filename;
    private String path;
    private String status;
    private String updatetime;
    private Integer inter_id;
    private String inter_name;
    private String stu_number;
    private String stu_name;


    public String getStu_number() {
        return stu_number;
    }

    public void setStu_number(String stu_number) {
        this.stu_number = stu_number;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public Integer getInter_id() {
        return inter_id;
    }

    public void setInter_id(Integer inter_id) {
        this.inter_id = inter_id;
    }

    public String getInter_name() {
        return inter_name;
    }

    public void setInter_name(String inter_name) {
        this.inter_name = inter_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Integer getIntercom_id() {
        return intercom_id;
    }

    public void setIntercom_id(Integer intercom_id) {
        this.intercom_id = intercom_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResumeInfo{" +
                "id=" + id +
                ", stu_id=" + stu_id +
                ", intercom_id=" + intercom_id +
                ", filename='" + filename + '\'' +
                ", path='" + path + '\'' +
                ", status='" + status + '\'' +
                ", updatetime=" + updatetime +
                '}';
    }
}
