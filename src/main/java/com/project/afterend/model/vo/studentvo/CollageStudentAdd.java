package com.project.afterend.model.vo.studentvo;

import com.project.afterend.model.vo.trainteachervo.TrainingCompanyAdd;

import java.util.List;

/*这一组是学生添加时所需要的级联选择器的工具类*/
public class CollageStudentAdd {
    private Integer id;
    private String name;
    private List<MajorStudentAdd> children;

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

    public List<MajorStudentAdd> getChildren() {
        return children;
    }

    public void setChildren(List<MajorStudentAdd> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CollageStudentAdd{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
