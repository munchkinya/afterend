package com.project.afterend.mapper;

import com.project.afterend.beans.Noticeinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeinfoMapper {
    List<Noticeinfo> list();//得到所有新闻
    Noticeinfo getByid(Integer id);//根据id得
    Integer updateNews(Noticeinfo noticeinfo);
    Integer updateimgurl(Map<String,Object> map);
    List<Noticeinfo> selectallstudentnews();
    Integer updatePageView(Integer id);
    List<Noticeinfo> getallslideshow();
    List<Noticeinfo> selectallpricenews();
    List<Noticeinfo> selectallpoticenews();
    List<Noticeinfo> selectallinternews();
    List<Noticeinfo> getallstudentNewsbypublisher(String name);
    List<Noticeinfo> getallpriceNewsbypublisher(String name);
    List<Noticeinfo> getallpoticeNewsbypublisher(String name);
    List<Noticeinfo> getallinterNewsbypublisher(String name);
    Integer insertNews(Noticeinfo noticeinfo);
    Integer deletenew(Integer notice_id);
}
