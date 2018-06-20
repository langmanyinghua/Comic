package com.mmm.comic.bean;

/**
 * Created by link on 2018/6/20.
 */

public class TabBead {
    private boolean isCheck;
    private String text;

    public TabBead(String text, boolean isCheck) {
        this.text = text;
        this.isCheck = isCheck;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
