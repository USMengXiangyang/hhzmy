package com.example.fragment;

import java.util.List;

/**
 * Created by asus on 2016/11/10.
 */
public class JsonBean {

    public String code;
    public String msg;
    public List<DataBean> data;
    public class DataBean{
        /*"id":"389",
                "goods_name":"清爽平衡矿物蚕丝面膜黑面膜21片",
                "shop_price":59.9,
                "market_price":297,
                "is_coupon_allowed":false,
                "is_allow_credit":false,
                "goods_img":"http://image.hmeili.com/yunifang/images/goods/389/goods_img/16102714013602977956224227.jpg",
                "sales_volume":93268,
                "efficacy":"以黑吸黑 净透亮肤",
                "watermarkUrl":"http://image.hmeili.com/yunifang/images/goods/temp/1610311933285253313545736.gif",
                "sort":0*/
        public String goods_name;
        public String goods_img;
        public String efficacy;
        public String watermarkUrl;
    }
}
