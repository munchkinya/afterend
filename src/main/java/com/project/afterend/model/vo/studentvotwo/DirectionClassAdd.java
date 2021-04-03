package com.project.afterend.model.vo.studentvotwo;

import java.util.List;

public class DirectionClassAdd {
    private Integer id;
    private String name;
    private List<TrainTeacherAdd> children;

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

    public List<TrainTeacherAdd> getChildren() {
        return children;
    }

    public void setChildren(List<TrainTeacherAdd> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "DirectionClassAdd{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
