package com.mmm.comic.bean;

/**
 * Created by 浪漫樱花 on 2018/6/20.
 */
public class HelpBean {
    private String title;
    private String url;


    public HelpBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HelpBean(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "HelpBean{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
