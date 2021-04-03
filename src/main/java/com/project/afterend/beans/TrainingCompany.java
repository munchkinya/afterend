package com.project.afterend.beans;

import java.util.List;

public class TrainingCompany {
    private Integer trainId;

    private String trainName;

    private String principal;

    private String trainTel;

    private Integer delflag;

    private Integer collageId;

    private String collagename;

    private String vueStatus;//前台

    public String getVueStatus() {
        return vueStatus;
    }

    public void setVueStatus(String vueStatus) {
        this.vueStatus = vueStatus;
    }

    private List<DirectionInfo> children;//为了级联选择器-》所在方向

    public List<DirectionInfo> getChildren() {
        return children;
    }

    public void setChildren(List<DirectionInfo> children) {
        this.children = children;
    }

    public String getCollagename() {
        return collagename;
    }

    public void setCollagename(String collagename) {
        this.collagename = collagename;
    }

    public Integer getCollageId() {
        return collageId;
    }

    public void setCollageId(Integer collageId) {
        this.collageId = collageId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName == null ? null : trainName.trim();
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public String getTrainTel() {
        return trainTel;
    }

    public void setTrainTel(String trainTel) {
        this.trainTel = trainTel == null ? null : trainTel.trim();
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    @Override
    public String toString() {
        return "TrainingCompany{" +
                "trainId=" + trainId +
                ", trainName='" + trainName + '\'' +
                ", principal='" + principal + '\'' +
                ", trainTel='" + trainTel + '\'' +
                ", delflag=" + delflag +
                ", collageId=" + collageId +
                ", collagename='" + collagename + '\'' +
                '}';
    }
}