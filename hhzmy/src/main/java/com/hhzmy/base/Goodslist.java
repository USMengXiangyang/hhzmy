package com.hhzmy.base;

/**
 * Created by asus on 2016/11/17.
 */
public class Goodslist {
    public String title;
    public String msg1;
    public int img_data;

    public Goodslist(String title, String msg1, int img_data) {
        this.title = title;
        this.msg1 = msg1;
        this.img_data = img_data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public int getImg_data() {
        return img_data;
    }

    public void setImg_data(int img_data) {
        this.img_data = img_data;
    }
}
