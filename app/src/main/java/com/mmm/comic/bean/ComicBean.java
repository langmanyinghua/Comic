package com.mmm.comic.bean;

import java.util.List;

/**
 * Created by 浪漫樱花 on 2018/6/19.
 */
public class ComicBean {
    private String title;
    private String thumb;
    private String author;
    private String tag;
    private String heat;
    private String detail;
    private List<SectionBean> sections;

    public ComicBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHeat() {
        return heat;
    }

    public void setHeat(String heat) {
        this.heat = heat;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<SectionBean> getSections() {
        return sections;
    }

    public void setSections(List<SectionBean> sections) {
        this.sections = sections;
    }

    public ComicBean(String title, String thumb, String author, String tag, String heat, String detail, List<SectionBean> sections) {
        this.title = title;
        this.thumb = thumb;
        this.author = author;
        this.tag = tag;
        this.heat = heat;
        this.detail = detail;
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "ComicBean{" +
                "title='" + title + '\'' +
                ", thumb='" + thumb + '\'' +
                ", author='" + author + '\'' +
                ", tag='" + tag + '\'' +
                ", heat='" + heat + '\'' +
                ", detail='" + detail + '\'' +
                ", sections=" + sections +
                '}';
    }
}
