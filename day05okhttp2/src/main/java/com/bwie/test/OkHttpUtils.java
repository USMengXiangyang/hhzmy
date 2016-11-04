package com.bwie.test;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by asus on 2016/11/4.
 */
public class OkHttpUtils {
    //设置标题头格式
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private static OkHttpClient mCliment;

    public static OkHttpClient getokHttpclient() {
       /* HttpLoggingInterceptor Inter = new HttpLoggingInterceptor();
        Inter.setLevel(HttpLoggingInterceptor.Level.BODY);*/
        mCliment = new OkHttpClient.Builder()
//                .addInterceptor(Inter)
                .build();
        return mCliment;
    }

    /**
     * 异步post  参数为对象
     * @param url
     * @param obj
     * @param callback
     */
    public static void post(String url, Object obj, Callback callback) {
        post(url, obj, callback);
    }
    /**
     * 异步为POST,参数为JSON
     *
     */
    public static void post(String url,String json,Callback callback){
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        getokHttpclient().newCall(request).enqueue(callback);
    }
    /**
     * 异步post,参数为map
     */

    public static  void post(String url, Map<String,String>map,Callback callback){
        FormBody.Builder body = new  FormBody.Builder();
        for (String key:map.keySet()){
                body.add(key,map.get(key));
            RequestBody requestBody = body.build();
            Request request =new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            getokHttpclient().newCall(request).enqueue(callback);
        }
    }
    /**
     * 异步get请求
     */
    public static void get(String url,Callback callback){
            Request request = new Request.Builder()
                    .url(url)
                    .build();
        getokHttpclient().newCall(request).enqueue(callback);
    }


}
