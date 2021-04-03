package com.project.afterend.beans;

import java.util.List;

public class CollegeInfo {
    private Integer coId;

    private String coName;

    private List<TrainingCompany> children;//所属学院-》所在公司,后面的都在其他类里面

    public List<TrainingCompany> getChildren() {
        return children;
    }

    public void setChildren(List<TrainingCompany> children) {
        this.children = children;
    }

    public Integer getCoId() {
        return coId;
    }

    public void setCoId(Integer coId) {
        this.coId = coId;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName == null ? null : coName.trim();
    }

    @Override
    public String toString() {
        return "CollegeInfo{" +
                "coId=" + coId +
                ", coName='" + coName + '\'' +
                ", children=" + children +
                '}';
    }
}