package com.hhzmy.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hhzmy.bean.CarData;
import com.hhzmy.datebase.TypeOpenHelper;

/**
 * Created by asus on 2016/11/19.
 */
public class TypeCoserDao {
    private static TypeOpenHelper helper;
    public TypeCoserDao(Context context){
        helper = new TypeOpenHelper(context);
    }
    public static void savetype(CarData carData){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("goodsname", carData.getGoodsName());
        values.put("goodsNewPrice", carData.getGoodsNewPrice());
        values.put("goodsCL", carData.getGoodsCL());

    }
}
