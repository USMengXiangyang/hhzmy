package com.example.day04listgrid;

/**
 * Created by asus on 2016/11/3.
 */
public class Bean2 {
    public int Img;
    public String name;
    public String shijian;

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }

    public Bean2(int img, String name, String shijian) {
        Img = img;
        this.name = name;
        this.shijian = shijian;
    }
}
