package com.project.afterend.beans;

public class WeekDiary {
    private Integer id;
    private Integer student_id;
    private String week;
    private String path;
    private String filename;
    private Integer status;
    private String stu_name;
    private String stu_number;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WeekDiary{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", week='" + week + '\'' +
                ", path='" + path + '\'' +
                ", filename='" + filename + '\'' +
                ", status=" + status +
                ", stu_name='" + stu_name + '\'' +
                ", stu_number='" + stu_number + '\'' +
                '}';
    }
}
