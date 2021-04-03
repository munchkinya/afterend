package com.project.afterend.service;

import com.project.afterend.beans.Noticeinfo;
import com.project.afterend.mapper.NoticeinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeinfoService {
    @Autowired
    NoticeinfoMapper noticeinfoMapper;
    //得到所有新闻
    public List<Noticeinfo> list(){
        return noticeinfoMapper.list();
    }
    //根据id得
    public Noticeinfo getByid(Integer id){
        return noticeinfoMapper.getByid(id);
    }
    public Integer updateNews(Noticeinfo noticeinfo){
        return noticeinfoMapper.updateNews(noticeinfo);
    }
    public Integer updateimgurl(Map<String,Object> map){
        return noticeinfoMapper.updateimgurl(map);
    }
    public List<Noticeinfo> selectallstudentnews(){
        return noticeinfoMapper.selectallstudentnews();
    }
    public Integer updatePageView(Integer id){
        return noticeinfoMapper.updatePageView(id);
    }
    public List<Noticeinfo> getallslideshow(){
        return noticeinfoMapper.getallslideshow();
    }
    public List<Noticeinfo> selectallpricenews(){
        return noticeinfoMapper.selectallpricenews();
    }
    public List<Noticeinfo> selectallpoticenews(){
        return noticeinfoMapper.selectallpoticenews();
    }
    public List<Noticeinfo> selectallinternews(){
        return noticeinfoMapper.selectallinternews();
    }
    public List<Noticeinfo> getallstudentNewsbypublisher(String name){
        return noticeinfoMapper.getallstudentNewsbypublisher(name);
    }
    public List<Noticeinfo> getallpriceNewsbypublisher(String name){
        return noticeinfoMapper.getallpriceNewsbypublisher(name);
    }
    public List<Noticeinfo> getallpoticeNewsbypublisher(String name){
        return noticeinfoMapper.getallpoticeNewsbypublisher(name);
    }
    public List<Noticeinfo> getallinterNewsbypublisher(String name){
        return noticeinfoMapper.getallinterNewsbypublisher(name);
    }
    public Integer insertNews(Noticeinfo noticeinfo){
        return noticeinfoMapper.insertNews(noticeinfo);
    }
    public Integer deletenew(Integer notice_id){
        return noticeinfoMapper.deletenew(notice_id);
    }
}
