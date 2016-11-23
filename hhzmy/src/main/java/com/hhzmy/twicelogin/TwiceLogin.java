package com.hhzmy.twicelogin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by asus on 2016/11/8.
 */
public class TwiceLogin {
    public static void setLogin(Context context, boolean flag){
        SharedPreferences sp = context.getSharedPreferences("main", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("is", flag);
        editor.commit();
    }
    public static boolean getLogin(Context context){
        SharedPreferences sp = context.getSharedPreferences("main", Context.MODE_PRIVATE);
        return sp.getBoolean("is", false);
    }

}
