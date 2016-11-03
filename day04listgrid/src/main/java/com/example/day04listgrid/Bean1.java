package com.example.day04listgrid;

/**
 * Created by asus on 2016/11/3.
 */
public class Bean1 {
    public String name;
    public int img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Bean1(String name, int img) {
        this.name = name;
        this.img = img;
    }
}
