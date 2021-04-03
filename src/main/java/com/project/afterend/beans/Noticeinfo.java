package com.project.afterend.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Noticeinfo {
    private Integer notice_id;
    private String title;
    private String body;
    private String kind;
    private String publisher;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishtime;
    private Integer pageview;
    private Integer isslideshow;
    private String imgurl;
    private String slideimgname;

    public String getSlideimgname() {
        return slideimgname;
    }

    public void setSlideimgname(String slideimgname) {
        this.slideimgname = slideimgname;
    }

    public Integer getPageview() {
        return pageview;
    }

    public void setPageview(Integer pageview) {
        this.pageview = pageview;
    }

    public Integer getIsslideshow() {
        return isslideshow;
    }

    public void setIsslideshow(Integer isslideshow) {
        this.isslideshow = isslideshow;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(Integer notice_id) {
        this.notice_id = notice_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    @Override
    public String toString() {
        return "Noticeinfo{" +
                "notice_id=" + notice_id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", kind='" + kind + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishtime=" + publishtime +
                ", pageview=" + pageview +
                ", isslideshow=" + isslideshow +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }
}
