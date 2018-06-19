package com.mmm.comic.bean;

import java.util.List;

/**
 * Created by 浪漫樱花 on 2018/6/19.
 */
public class SectionBean {
    private String title;
    private List<String> urls;

    public SectionBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public SectionBean(String title, List<String> urls) {
        this.title = title;
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "SectionBean{" +
                "title='" + title + '\'' +
                ", urls=" + urls +
                '}';
    }
}
