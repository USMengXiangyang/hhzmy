package com.bwie.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
//    private String str=null;
    private Handler hander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            tv.setText(str);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        postObj();
//        postkey();
        getData();
//        setText();


    }




    private void getData() {
        OkHttpUtils.get("http://www.tngou.net/api/cook/list", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                final SpannableString span = new SpannableString(str);
                span.setSpan(new URLSpan("http//:www.baidu.com"),68,77, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(str);
                        tv.setText(span);
                    }
                });

            }
        });

    }

    private void postkey() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("rows", "1");
        OkHttpUtils.post("http://www.tngou.net/api/cook/list", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                Message msg = Message.obtain();
                msg.obj = content;
                hander.sendMessage(msg);

            }
        });


    }

    private void postObj() {
//        String json = "{\\\"page\\\":\\\"1\\\",\\\"rows\\\":\\\"20\\\"}";
        OkHttpUtils.post("http://www.tngou.net/api/cook/list", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(result);
                    }
                });
            }
        });

    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);

    }


}
