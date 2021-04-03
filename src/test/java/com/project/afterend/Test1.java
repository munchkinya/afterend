package com.project.afterend;

import com.project.afterend.beans.StudentInfo;
import com.project.afterend.beans.TrainingTeacher;
import com.project.afterend.service.StudentInfoService;
import com.project.afterend.service.TrainingTeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {
    @Autowired
    TrainingTeacherService trainingTeacherService;
    @Autowired
    StudentInfoService studentInfoService;

    @Test
    public void test()  throws Exception {

        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        System.out.println(path);

    }
}
