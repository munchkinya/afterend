package com.project.afterend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.afterend.beans.Noticeinfo;
import com.project.afterend.service.InternshipCompanyService;
import com.project.afterend.service.NoticeinfoService;
import com.project.afterend.service.SchoolTeacherService;
import com.project.afterend.service.TrainingTeacherService;
import com.project.afterend.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Comparator;

@RestController
@RequestMapping("/api/news")
public class NoticeinfoController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Logger log = LoggerFactory.getLogger("FileController");

    @Autowired
    NoticeinfoService noticeinfoService;
    @Autowired
    SchoolTeacherService schoolTeacherService;
    @Autowired
    TrainingTeacherService trainingTeacherService;
    @Autowired
    InternshipCompanyService internshipCompanyService;

    //得到所有新闻的列表
    @GetMapping(value = "/getAllNews")
    public PageInfo<Noticeinfo> getAll(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Noticeinfo> list = noticeinfoService.list();
        PageInfo<Noticeinfo> page = new PageInfo(list);
        return page;
    }
    //点击进去看某一个具体的新闻信息
    @GetMapping(value = "/getbyid")
    public Noticeinfo get(@Valid @RequestParam("id") Integer id) {
        Noticeinfo noticeinfo=noticeinfoService.getByid(id);
        return noticeinfo;
    }
    //修改某一个新闻信息
    @PostMapping(value = "/news")
    public Noticeinfo updatenews(@RequestBody Noticeinfo noticeinfo) {
        noticeinfoService.updateNews(noticeinfo);
        return noticeinfoService.getByid(noticeinfo.getNotice_id());
    }
    //增加某一个新闻信息
    @PostMapping(value = "/addnews")
    public Integer addnews(@RequestBody Noticeinfo noticeinfo) {
        String username=noticeinfo.getPublisher();
        if(!username.equals("admin")){
            if(noticeinfo.getKind().equals("student")||noticeinfo.getKind().equals("price")){
                noticeinfo.setPublisher(schoolTeacherService.selectBysteachNumber(username).getSteachName());
            }else if(noticeinfo.getKind().equals("potice")){
                noticeinfo.setPublisher(trainingTeacherService.selectByUserName(username).getTteachName());
            }else if(noticeinfo.getKind().equals("inter")){
                noticeinfo.setPublisher(internshipCompanyService.selectByUserName(username).getPrincipal());
            }
        }
        noticeinfo.setPublishtime(new Date());
        noticeinfoService.insertNews(noticeinfo);
        return noticeinfo.getNotice_id();
    }
    //删除某一个新闻信息
    @GetMapping(value = "/deletenew")
    public String deletenew(@Valid @RequestParam("notice_id") Integer notice_id) {
        if(noticeinfoService.deletenew(notice_id)!=0){
            return "success";
        }else {
            return "error";
        }
    }
    //这里是单独处理的上传新闻的轮播图片
    @PostMapping("/uploaer")
    public String fileUploads(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            /*在这里得换下路径，存到static里面去*/
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/data/slideshowicons/";
            String id = request.getParameter("id");
            String format = sdf.format(new Date());
            try {
                FileUtil.fileupload(file.getBytes(), path, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> parameterMap=new HashMap<String, Object>();
            parameterMap.put("imgurl",path+fileName);
            parameterMap.put("notice_id",Integer.parseInt(id));
            parameterMap.put("slideimgname",fileName);
            if(noticeinfoService.updateimgurl(parameterMap)!=0){
                log.info("上传成功，当前上传的文件保存在 {}",path+fileName);
                return "success";
            }else{
                log.error("上传失败");
                return "fail";
            }
        }
        return "fail";
    }
    //得到学校相关新闻类的所有新闻
    @GetMapping(value = "/getallstudentNews")
    public PageInfo<Noticeinfo> getallstudentNews(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Noticeinfo> list = noticeinfoService.selectallstudentnews();
        //处理日期，按照日期降序返回列表
        Comparator<Noticeinfo> comp = new Comparator<com.project.afterend.beans.Noticeinfo>() {
            @Override
            public int compare(Noticeinfo o1, Noticeinfo o2) {
                if(o1.getPublishtime().before(o2.getPublishtime())){
                    return 1;
                }else if(o1.getPublishtime().after(o2.getPublishtime())){
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(list, comp);
        PageInfo<Noticeinfo> page = new PageInfo(list);
        return page;
    }
    //根据发布人的姓名得到学校相关新闻类的所有新闻
    @GetMapping(value = "/getallstudentNewsbypublisher")
    public PageInfo<Noticeinfo> getallstudentNewsbypublisher(@Valid @RequestParam("pageNum") Integer pageNum,
                                                             @Valid @RequestParam("pageSize") Integer pageSize,
                                                             @Valid @RequestParam("query") String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<Noticeinfo> list = noticeinfoService.getallstudentNewsbypublisher(schoolTeacherService.selectBysteachNumber(query).
                getSteachName());
        PageInfo<Noticeinfo> page = new PageInfo(list);
        return page;
    }

    //根据发布人的姓名得到实习相关新闻类的所有新闻
    @GetMapping(value = "/getallpriceNewsbypublisher")
    public PageInfo<Noticeinfo> getallpriceNewsbypublisher(@Valid @RequestParam("pageNum") Integer pageNum,
                                                             @Valid @RequestParam("pageSize") Integer pageSize,
                                                             @Valid @RequestParam("query") String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<Noticeinfo> list = noticeinfoService.getallpriceNewsbypublisher(schoolTeacherService.selectBysteachNumber(query).
                getSteachName());
        PageInfo<Noticeinfo> page = new PageInfo(list);
        return page;
    }
    //根据发布人的姓名得到实训相关新闻类的所有新闻
    @GetMapping(value = "/getallpoticeNewsbypublisher")
    public PageInfo<Noticeinfo> getallpoticeNewsbypublisher(@Valid @RequestParam("pageNum") Integer pageNum,
                                                           @Valid @RequestParam("pageSize") Integer pageSize,
                                                           @Valid @RequestParam("query") String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<Noticeinfo> list = noticeinfoService.getallpoticeNewsbypublisher(trainingTeacherService.selectByUserName(query).
                getTteachName());
        PageInfo<Noticeinfo> page = new PageInfo(list);
        return page;
    }
    //根据发布人的姓名得到实训相关新闻类的所有新闻
    @GetMapping(value = "/getallinterNewsbypublisher")
    public PageInfo<Noticeinfo> getallinterNewsbypublisher(@Valid @RequestParam("pageNum") Integer pageNum,
                                                            @Valid @RequestParam("pageSize") Integer pageSize,
                                                            @Valid @RequestParam("query") String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<Noticeinfo> list = noticeinfoService.getallinterNewsbypublisher(internshipCompanyService.selectByUserName(query).
                getPrincipal());
        PageInfo<Noticeinfo> page = new PageInfo(list);
        return page;
    }
    //得到实训相关新闻类的所有新闻
    @GetMapping(value = "/getallpoticeNews")
    public PageInfo<Noticeinfo> getallpoticeNews(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Noticeinfo> list = noticeinfoService.selectallpoticenews();
        //处理日期，按照日期降序返回列表
        Comparator<Noticeinfo> comp = new Comparator<com.project.afterend.beans.Noticeinfo>() {
            @Override
            public int compare(Noticeinfo o1, Noticeinfo o2) {
                if(o1.getPublishtime().before(o2.getPublishtime())){
                    return 1;
                }else if(o1.getPublishtime().after(o2.getPublishtime())){
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(list, comp);
        PageInfo<Noticeinfo> page = new PageInfo(list);
        return page;
    }
    //得到实训相关新闻类的所有新闻
    @GetMapping(value = "/getallpriceNews")
    public PageInfo<Noticeinfo> getallpriceNews(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Noticeinfo> list = noticeinfoService.selectallpricenews();
        //处理日期，按照日期降序返回列表
        Comparator<Noticeinfo> comp = new Comparator<com.project.afterend.beans.Noticeinfo>() {
            @Override
            public int compare(Noticeinfo o1, Noticeinfo o2) {
                if(o1.getPublishtime().before(o2.getPublishtime())){
                    return 1;
                }else if(o1.getPublishtime().after(o2.getPublishtime())){
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(list, comp);
        PageInfo<Noticeinfo> page = new PageInfo(list);
        return page;
    }
    //得到实训相关新闻类的所有新闻
    @GetMapping(value = "/getallinterNews")
    public PageInfo<Noticeinfo> getallinterNews(@Valid @RequestParam("pageNum") Integer pageNum, @Valid @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Noticeinfo> list = noticeinfoService.selectallinternews();
        //处理日期，按照日期降序返回列表
        Comparator<Noticeinfo> comp = new Comparator<com.project.afterend.beans.Noticeinfo>() {
            @Override
            public int compare(Noticeinfo o1, Noticeinfo o2) {
                if(o1.getPublishtime().before(o2.getPublishtime())){
                    return 1;
                }else if(o1.getPublishtime().after(o2.getPublishtime())){
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(list, comp);
        PageInfo<Noticeinfo> page = new PageInfo(list);
        return page;
    }
    /*每点击一次，增加新闻的浏览量*/
    @GetMapping(value = "/updatePageView")
    public void updatePageView(@Valid @RequestParam("id") Integer id) {
        noticeinfoService.updatePageView(id);
    }
    //得到带有轮播图的新闻，按照发布日期排序只会返回四个
    @GetMapping(value = "/getslideshownews")
    public List<Noticeinfo> getslideshownews() {
        List<Noticeinfo> list=noticeinfoService.getallslideshow();
        Comparator<Noticeinfo> comp = new Comparator<com.project.afterend.beans.Noticeinfo>() {
            @Override
            public int compare(Noticeinfo o1, Noticeinfo o2) {
                if(o1.getPublishtime().before(o2.getPublishtime())){
                    return 1;
                }else if(o1.getPublishtime().after(o2.getPublishtime())){
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(list, comp);
        List<Noticeinfo> list1=new ArrayList<>();
        list1.add(list.get(0));
        list1.add(list.get(1));
        list1.add(list.get(2));
        list1.add(list.get(3));
        return list1;
    }
    //新闻首页，返回不带分页的学校新闻列表，返回五个
    @GetMapping(value = "/getstudentnewsindex")
    public List<Noticeinfo> getstudentnewsindex() {
        List<Noticeinfo> list=noticeinfoService.selectallstudentnews();
        Comparator<Noticeinfo> comp = new Comparator<com.project.afterend.beans.Noticeinfo>() {
            @Override
            public int compare(Noticeinfo o1, Noticeinfo o2) {
                if(o1.getPublishtime().before(o2.getPublishtime())){
                    return 1;
                }else if(o1.getPublishtime().after(o2.getPublishtime())){
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(list, comp);
        List<Noticeinfo> list1=new ArrayList<>();
        list1.add(list.get(0));
        list1.add(list.get(1));
        list1.add(list.get(2));
        list1.add(list.get(3));
        list1.add(list.get(4));
        return list1;
    }
    //新闻首页，返回不带分页的实训新闻列表，返回五个
    @GetMapping(value = "/selectallpoticenews")
    public List<Noticeinfo> selectallpoticenews() {
        List<Noticeinfo> list=noticeinfoService.selectallpoticenews();
        Comparator<Noticeinfo> comp = new Comparator<com.project.afterend.beans.Noticeinfo>() {
            @Override
            public int compare(Noticeinfo o1, Noticeinfo o2) {
                if(o1.getPublishtime().before(o2.getPublishtime())){
                    return 1;
                }else if(o1.getPublishtime().after(o2.getPublishtime())){
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(list, comp);
        List<Noticeinfo> list1=new ArrayList<>();
        list1.add(list.get(0));
        list1.add(list.get(1));
        list1.add(list.get(2));
        list1.add(list.get(3));
        list1.add(list.get(4));
        return list1;
    }

    //新闻首页，返回不带分页的实训新闻列表，返回五个
    @GetMapping(value = "/getinternewsindex")
    public List<Noticeinfo> getinternewsindex() {
        List<Noticeinfo> list=noticeinfoService.selectallinternews();
        Comparator<Noticeinfo> comp = new Comparator<com.project.afterend.beans.Noticeinfo>() {
            @Override
            public int compare(Noticeinfo o1, Noticeinfo o2) {
                if(o1.getPublishtime().before(o2.getPublishtime())){
                    return 1;
                }else if(o1.getPublishtime().after(o2.getPublishtime())){
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(list, comp);
        List<Noticeinfo> list1=new ArrayList<>();
        list1.add(list.get(0));
        list1.add(list.get(1));
        list1.add(list.get(2));
        list1.add(list.get(3));
        list1.add(list.get(4));
        return list1;
    }

}
