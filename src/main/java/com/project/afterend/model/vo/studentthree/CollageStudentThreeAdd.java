package com.project.afterend.model.vo.studentthree;

import com.project.afterend.model.vo.studentvo.MajorStudentAdd;

import java.util.List;

/*这一组是学生添加时所需要的级联选择器的工具类*/
public class CollageStudentThreeAdd {
    private Integer id;
    private String name;
    private List<SchoolTeacherStudentAdd> children;

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

    public List<SchoolTeacherStudentAdd> getChildren() {
        return children;
    }

    public void setChildren(List<SchoolTeacherStudentAdd> children) {
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
