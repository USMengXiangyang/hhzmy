package com.hhzmy.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus on 2016/11/19.
 */
public class TypeOpenHelper extends SQLiteOpenHelper {
    public TypeOpenHelper(Context context) {
        super(context, "cardata.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table car(goodsName varchar(10),goodsNewPrice integer(20),refPrice integer(20),goodsCL varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
