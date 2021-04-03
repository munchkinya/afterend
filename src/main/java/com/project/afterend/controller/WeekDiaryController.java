package com.project.afterend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.afterend.beans.*;
import com.project.afterend.service.*;
import com.project.afterend.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.*;

@RestController
@RequestMapping("/api/weekdiary")
public class WeekDiaryController {
    private Logger log = LoggerFactory.getLogger("WeekDiaryController");

    @Autowired
    WeekDiaryService weekDiaryService;
    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    SchoolTeacherService schoolTeacherService;
    @Autowired
    TrainingTeacherService trainingTeacherService;

    /*得到所有的周记表*/
    @GetMapping(value = "/getAllweekdiary")
    public PageInfo<WeekDiary> getAll(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize, @Valid @RequestParam("stuId") String stuId) {
        Integer stu_id=studentInfoService.selectByStudentName(stuId).getStuId();//这里不是学号，是表的主键
        PageHelper.startPage(pageNum, pageSize);
        List<WeekDiary> list = weekDiaryService.getAllWeekDiaryByID(stu_id);
        PageInfo<WeekDiary> page = new PageInfo(list);
        return page;
    }
    /*学生上传周记表*/
    @PostMapping("/upload")
    public String fileUploads(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String path = ResourceUtils.getURL("static").getPath()+"studentWeekdiarys/";
            String id = request.getParameter("upload_id");
            try {
                FileUtil.fileupload(file.getBytes(), path, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            WeekDiary weekDiary=new WeekDiary();
            weekDiary.setFilename(fileName);
            weekDiary.setPath(path+fileName);
            weekDiary.setId(Integer.parseInt(id));
            if(weekDiaryService.updateByID(weekDiary)!=0){
                log.info("上传成功，当前上传的文件保存在 {}",path+fileName);
                return "success";
            }else{
                log.error("上传失败");
                return "fail";
            }
        }
        return "fail";
    }
    /*教师查看实习周记，这里会有这些情况1.学校普通教师：只会查看到自己所带学生的周记
    * 2.学院领导，直接可以查看本学院的全部学生周记
    * 3.实训公司普通教师，可以查看本班所有学生周记
    * 4.实训公司领导，可以查看本院全部学生周记*/
    @GetMapping(value = "/getstudentweekdiary")
    public PageInfo<WeekDiary> getstudentweekdiary(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize")
            Integer pageSize, @Valid @RequestParam("query") String query,@Valid @RequestParam("teacherId") String teacherId,
            @Valid @RequestParam("roleId") Integer roleId, @Valid @RequestParam("studentName") String studentName) {
        //这需要前端传过来教师的id和query，query主要是第几周，然后再去匹配
        /*先根据id查角色，然后看调用哪个表*/
        List<Integer> idlist = null;
        if(roleId==3){//学院领导
            idlist=new ArrayList<>();
            SchoolTeacher schoolTeacher=schoolTeacherService.selectBysteachNumber(teacherId);//得到当前角色id
            List<StudentInfo> studentlist=studentInfoService.selectByCollage(schoolTeacher.getSteachId());//得到学生列表
            for(StudentInfo studentInfo:studentlist){
                idlist.add(studentInfo.getStuId());
            }
        }else if(roleId==4){//学院普通教师
            idlist=new ArrayList<>();
            SchoolTeacher schoolTeacher=schoolTeacherService.selectBysteachNumber(teacherId);//得到当前角色id
            List<StudentInfo> studentlist=studentInfoService.selectBySTNumber(schoolTeacher.getSteachId());//得到学生列表
            for(StudentInfo studentInfo:studentlist){
                idlist.add(studentInfo.getStuId());
            }
        }else if(roleId==5){//实训公司领导
            idlist=new ArrayList<>();
            TrainingTeacher trainingTeacher=trainingTeacherService.selectByUserName(teacherId);//得到当前角色id
            List<StudentInfo> studentlist=studentInfoService.selectByTrainCom(trainingTeacher.getTteachId());//得到学生列表
            for(StudentInfo studentInfo:studentlist){
                idlist.add(studentInfo.getStuId());
            }
        }else if(roleId==6){//实训公司普通教师
            idlist=new ArrayList<>();
            TrainingTeacher trainingTeacher=trainingTeacherService.selectByUserName(teacherId);//得到当前角色id
            List<StudentInfo> studentlist=studentInfoService.selectByTTNumber(trainingTeacher.getTteachId());//得到学生列表
            for(StudentInfo studentInfo:studentlist){
                idlist.add(studentInfo.getStuId());
            }
        }else{
            System.out.println("传入的roleid不对");
        }
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> parameterMap=new HashMap<String, Object>();
        parameterMap.put("studentName", studentName);
        parameterMap.put("query", query);
        parameterMap.put("list", idlist);
        ArrayList<WeekDiary> list=weekDiaryService.getAllWeekDiary(parameterMap);
        PageInfo<WeekDiary> page = new PageInfo(list);
        return page;
    }
    /*其他人下载周记表*/
    @PostMapping("/downloaddiary")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getHeader("id");
        WeekDiary weekDiary=weekDiaryService.selectByKey(Integer.parseInt(id));
        if(weekDiary==null){
            System.out.println("获取简历路径出错了");
        }
        String filepath="static/studentWeekdiarys/"+weekDiary.getFilename();
        File file = new File(filepath);//被下载的文件在服务器中的路径,
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null; //输出流
        try {
            //判断文件父目录是否存在
            if (file.exists()) {
                //设置返回文件信息
                response.setContentType("application/vnd.ms-excel;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode("1.pdf", "UTF-8"));
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                while (bis.read(buffer) != -1) {
                    os.write(buffer);
                }
                return "sucess";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "fail";
    }

}
