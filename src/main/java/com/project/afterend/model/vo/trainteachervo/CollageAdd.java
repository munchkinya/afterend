package com.project.afterend.model.vo.trainteachervo;

import java.util.List;

/*级联选择器的时候用的*/
public class CollageAdd {
    private Integer id;
    private String name;
    private List<TrainingCompanyAdd> children;//所属学院-》所在公司,后面的都在其他类里面

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

    public List<TrainingCompanyAdd> getChildren() {
        return children;
    }

    public void setChildren(List<TrainingCompanyAdd> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CollageAdd{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
