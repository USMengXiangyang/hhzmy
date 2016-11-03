package com.example.day04listgrid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int[] imgs = new int[]{R.drawable.gv1, R.drawable.gv2, R.drawable.gv3, R.drawable.gv4, R.drawable.gv5, R.drawable.gv6, R.drawable.gv7, R.drawable.gv8, R.drawable.gv9, R.drawable.gv10, R.drawable.gv11, R.drawable.gv12, R.drawable.gv13, R.drawable.gv14, R.drawable.gv1, R.drawable.gv3};
    String[] names = new String[]{"第四届“甘肃黄河文学奖”拟获奖名单公示","第四届“甘肃黄河文学奖”拟获奖名单公示","第四届“甘肃黄河文学奖”拟获奖名单公示","第四届“甘肃黄河文学奖”拟获奖名单公示","第四届“甘肃黄河文学奖”拟获奖名单公示","第四届“甘肃黄河文学奖”拟获奖名单公示","第四届“甘肃黄河文学奖”拟获奖名单公示","第四届“甘肃黄河文学奖”拟获奖名单公示","第四届“甘肃黄河文学奖”拟获奖名单公示","第四届“甘肃黄河文学奖”拟获奖名单公示"};
    //GridView数据集合
    List<Bean1> data = new ArrayList<>();
    //ListView数据集合
    List<Bean2> data2 = new ArrayList<>();
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载数据1
        initData();
        //加载数据2
        initData2();



    }

    private void initData2() {
        //时间设置
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        time = format.format(new Date());
        for (int i=0;i<10;i++){
            data2.add(new Bean2(R.mipmap.ic_launcher,names[i],time));
        }
        ListView lv = (ListView) findViewById(R.id.lv);
        MyListAdapter liada = new MyListAdapter(data2,this);
        lv.setAdapter(liada);

    }

    private void initData() {
        for (int i = 0; i <= 15; i++) {
            data.add(new Bean1("作家协会" + i, imgs[i]));
            ;
        }
        GridView gv = (GridView) findViewById(R.id.gv);
        MyGridAdapter ada = new MyGridAdapter(data,this);
        gv.setAdapter(ada);

    }


}
