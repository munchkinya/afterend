package com.project.afterend.model.vo.trainteachervo;

import java.util.List;

public class DirectionInfoAdd {
    private Integer id;
    private String name;
    private List<DirectionClassAdd> children;//为了级联选择器，所在班级

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

    public List<DirectionClassAdd> getChildren() {
        return children;
    }

    public void setChildren(List<DirectionClassAdd> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "DirectionInfoAdd{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
