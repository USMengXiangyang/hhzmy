package com.hhzmy.bean;

/**
 * Created by asus on 2016/11/19.
 */
public class CarData {
    public String goodsName;
    public Double goodsNewPrice;
    public Double refPrice;
    public String goodsCL;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsNewPrice() {
        return goodsNewPrice;
    }

    public void setGoodsNewPrice(Double goodsNewPrice) {
        this.goodsNewPrice = goodsNewPrice;
    }

    public Double getRefPrice() {
        return refPrice;
    }

    public void setRefPrice(Double refPrice) {
        this.refPrice = refPrice;
    }

    public String getGoodsCL() {
        return goodsCL;
    }

    public void setGoodsCL(String goodsCL) {
        this.goodsCL = goodsCL;
    }

    public CarData(String goodsName, Double goodsNewPrice, Double refPrice, String goodsCL) {
        this.goodsName = goodsName;
        this.goodsNewPrice = goodsNewPrice;
        this.refPrice = refPrice;
        this.goodsCL = goodsCL;
    }

    public CarData() {
    }
}
