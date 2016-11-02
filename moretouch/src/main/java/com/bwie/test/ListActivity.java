package com.bwie.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<String> list;
    private  CheckBox all;
    TextView hide;
    TextView refect;
    boolean isHide = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initData();

        all = (CheckBox) findViewById(R.id.All);
        hide = (TextView) findViewById(R.id.hide);
        refect = (TextView) findViewById(R.id.refect);
        ListView lv = (ListView) findViewById(R.id.lv);
        final MyAdapter adapter = new MyAdapter(list,this,all,false);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.linkedList.set(i, !adapter.linkedList.get(i));
                // 点击的同时去判断是否全选
                if (adapter.linkedList.contains(false)) {
                    all.setChecked(false);
                } else {
                    all.setChecked(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
        //设置全选
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean allis = all.isChecked();
                for (int i=0;i<adapter.getSelect().size();i++){
                    adapter.getSelect().set(i,allis);
                }
                adapter.notifyDataSetChanged();
            }

        });
        refect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!adapter.getSelect().contains(true)){
                    Toast.makeText(ListActivity.this, "请选择！！", Toast.LENGTH_SHORT).show();
                }else{
                    //有选中的，则选中的改变值，而未选中的也会改变值
                    for (int i=0;i<adapter.getSelect().size();i++){
                            //判断选择器是哪个选中的
                        if(adapter.getSelect().get(i)){
                            adapter.getSelect().set(i,false);
                        }else{
                            adapter.getSelect().set(i,true);
                        }
                    }
                    //结果全选中，则改变全选框的值
                    if(adapter.getSelect().contains(false)){
                        all.setChecked(false);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHide){
                    refect.setText("取消");
                }else{
                    refect.setText("隐藏");
                }
                adapter.setHide(false);
            }
        });

    }

    private void initData() {
        //数据
        list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            list.add("这是条目" + i);
        }
    }




}
