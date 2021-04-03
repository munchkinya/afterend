package com.project.afterend.model.vo.studentvotwo;

import java.util.List;

public class TrainingCompanyAdd {
    private Integer id;
    private String name;
    private List<DirectionInfoAdd> children;//为了级联选择器-》所在方向

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

    public List<DirectionInfoAdd> getChildren() {
        return children;
    }

    public void setChildren(List<DirectionInfoAdd> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TrainingCompanyAdd{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
