package com.project.afterend.model.vo.studentvo;

import com.project.afterend.model.vo.trainteachervo.TrainingCompanyAdd;

import java.util.List;

public class MajorStudentAdd {
    private Integer id;
    private String name;
    private List<MajorClassStudentAdd> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MajorClassStudentAdd> getChildren() {
        return children;
    }

    public void setChildren(List<MajorClassStudentAdd> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MajorStudentAdd{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
