package com.project.afterend.beans;

import java.util.List;

public class DirectionInfo {
    private Integer diId;

    private String diName;

    private Integer trainId;

    private Integer coId;

    private List<DirectionClass> children;//为了级联选择器，所在班级

    public List<DirectionClass> getChildren() {
        return children;
    }

    public void setChildren(List<DirectionClass> children) {
        this.children = children;
    }

    public Integer getDiId() {
        return diId;
    }

    public void setDiId(Integer diId) {
        this.diId = diId;
    }

    public String getDiName() {
        return diName;
    }

    public void setDiName(String diName) {
        this.diName = diName == null ? null : diName.trim();
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getCoId() {
        return coId;
    }

    public void setCoId(Integer coId) {
        this.coId = coId;
    }
}