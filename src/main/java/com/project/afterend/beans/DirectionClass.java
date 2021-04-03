package com.project.afterend.beans;

public class DirectionClass {
    private Integer dclassId;

    private String dclassName;

    private Integer grade;

    private Integer classCount;

    private Integer diId;

    public Integer getDclassId() {
        return dclassId;
    }

    public void setDclassId(Integer dclassId) {
        this.dclassId = dclassId;
    }

    public String getDclassName() {
        return dclassName;
    }

    public void setDclassName(String dclassName) {
        this.dclassName = dclassName == null ? null : dclassName.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getClassCount() {
        return classCount;
    }

    public void setClassCount(Integer classCount) {
        this.classCount = classCount;
    }

    public Integer getDiId() {
        return diId;
    }

    public void setDiId(Integer diId) {
        this.diId = diId;
    }
}