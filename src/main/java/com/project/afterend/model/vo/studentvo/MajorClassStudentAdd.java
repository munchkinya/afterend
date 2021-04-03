package com.project.afterend.model.vo.studentvo;

import com.project.afterend.model.vo.trainteachervo.TrainingCompanyAdd;

import java.util.List;

public class MajorClassStudentAdd {
    private Integer id;
    private String name;

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

    @Override
    public String toString() {
        return "MajorClassStudentAdd{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
