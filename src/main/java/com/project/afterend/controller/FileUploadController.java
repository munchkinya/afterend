package com.project.afterend.controller;

import com.project.afterend.service.FilePathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class FileUploadController {

    @Autowired
    private FilePathService filePathService;

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        return filePathService.Upload(file);
    }
}
/*
package com.project.afterend.controller;

        import java.io.File;
        import java.io.IOException;
        import java.util.UUID;
        import javax.servlet.http.HttpServletRequest;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.multipart.MultipartFile;
        import java.text.SimpleDateFormat;
        import org.springframework.beans.factory.annotation.Value;
        import java.util.Date;
        import org.slf4j.*;

@RestController
@RequestMapping("/uploaderfile")
public class FileController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String filePath="D:/uploaderfile/";
    private Logger log = LoggerFactory.getLogger("FileController");
    @PostMapping("/upload")
    public String fileUploads(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        String stuId = request.getParameter("stu_id");
        String format = sdf.format(new Date());
        String fileName = file.getOriginalFilename();
        String newFileName = format + "_" + fileName;
        File dest = new File(filePath + newFileName);
        try {
            file.transferTo(dest);
            log.info("上传成功，当前上传的文件保存在 {}",filePath + newFileName);
            return "success";
        } catch (IOException e) {
            log.error(e.toString());
        }
        return "fail";
    }
}*/
