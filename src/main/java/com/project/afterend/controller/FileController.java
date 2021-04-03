package com.project.afterend.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.afterend.beans.ResumeInfo;
import com.project.afterend.mapper.ResumeInfoMapper;
import com.project.afterend.service.ResumeInfoService;
import com.project.afterend.service.StudentInfoService;
import com.project.afterend.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.*;

/*实现所有相关功能的上传、下载文件的controller类*/
@RestController
@RequestMapping("/uploaderfile")
public class FileController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Logger log = LoggerFactory.getLogger("FileController");
    @Autowired
    private ResumeInfoService resumeInfoService;
    @Autowired
    private StudentInfoService studentInfoService;

    @PostMapping("/upload")
    public String fileUploads(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            if(resumeInfoService.getResumeByName(fileName)!=null){
                return "again";
            }
            String path = ResourceUtils.getURL("static").getPath()+"studentResumes/";
            String stuId = request.getParameter("stu_id");
            String intercom_id = request.getParameter("intercom_id");
            String format = sdf.format(new Date());
            try {
                FileUtil.fileupload(file.getBytes(), path, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ResumeInfo resumeInfo = new ResumeInfo();
            resumeInfo.setStu_id(studentInfoService.selectByStudentName(stuId).getStuId());//这里不是学号，是表的主键
            resumeInfo.setIntercom_id(Integer.parseInt(intercom_id));
            resumeInfo.setFilename(fileName);
            resumeInfo.setPath(path+fileName);
            resumeInfo.setUpdatetime(format);
            /*System.out.println(resumeInfo.toString());*/
            if(resumeInfoService.insertResume(resumeInfo)!=0){
                log.info("上传成功，当前上传的文件保存在 {}",path+fileName);
                return "success";
            }else{
                log.error("上传失败");
                return "fail";
            }
        }
        return "fail";
    }
    /*这里是全部简历下载的区域*/
    @PostMapping("/downloadresume")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getHeader("id");
        ResumeInfo resumeInfo=resumeInfoService.selectById(Integer.parseInt(id));
        if(resumeInfo==null){
            System.out.println("获取简历路径出错了");
        }
        String filepath="static/studentResumes/"+resumeInfo.getFilename();
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