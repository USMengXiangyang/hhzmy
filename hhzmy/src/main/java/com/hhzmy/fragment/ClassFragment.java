package com.hhzmy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.hhzmy.adaper.MyRecyclerContextAdapter;
import com.hhzmy.adaper.MyRecyclerLineAdapter;
import com.hhzmy.bean.MyBean;
import com.hhzmy.test.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by asus on 2016/11/8.
 */
public class ClassFragment extends Fragment {
    private List<MyBean.RsBean> list=new ArrayList();
    private List<MyBean.RsBean.ChildrenBean> childrenList = new ArrayList<>();
    int index=0;
    private MyRecyclerLineAdapter adapter;
    private MyRecyclerContextAdapter contextAdapter;
    private RecyclerView recycleContext;
    private View view;
    private RecyclerView recycleLine;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.class_fragment, null);
        initData();
        setleftrecycle();
        setRightRecycle();
        return view;
    }

    /**
     * 右边内容设置
     */
    private void setRightRecycle() {
        recycleContext = (RecyclerView) view.findViewById(R.id.recycle_context);
        GridLayoutManager manager=new GridLayoutManager(getActivity(),3);
        //设置布局管理器
        recycleContext.setLayoutManager(manager);
        //设置垂直
        manager.setOrientation(OrientationHelper.VERTICAL);
        //设置只有字体占行数
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //判断是否为标题 ，是 返回3   否  返回1
                return childrenList.get(position).isHeader?3:1;
            }
        });
        //默认将第一个数据进行遍历展示
        List<MyBean.RsBean.ChildrenBean>  childrenBeanList=list.get(0).children;
        for (int i = 0; i < childrenBeanList.size(); i++) {
            childrenBeanList.get(i).isHeader = true;
            childrenList.add(childrenBeanList.get(i));
            childrenList.addAll(childrenBeanList.get(i).children);
        }

        //设置适配器
        contextAdapter = new MyRecyclerContextAdapter(getActivity(),childrenList);
        recycleContext.setAdapter(contextAdapter);
    }

    /**
     * 获取数据
     */
    private void initData() {
        try {
            //读取本地文件
            InputStream inputStream = getResources().getAssets().open("category.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String str = null;
            while((str = br.readLine())!=null){
                stringBuffer.append(str);
            }
            str=stringBuffer.toString();
            Log.e("bean---------",str);
            Gson gson=new Gson();
            MyBean myBean = gson.fromJson(str, MyBean.class);

            list.addAll(myBean.rs);
            //默认第一个选中
            list.get(0).ischeck=true;
            Log.e("data",myBean.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

   /* @OnClick({R.id.editext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editext:
                break;
        }
    }*/


    /**
     * 设置分类
     * 左边导航栏
     */
    private void setleftrecycle() {
        recycleLine = (RecyclerView) view.findViewById(R.id.recycle_line);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        //设置布局管理器
        recycleLine.setLayoutManager(manager);
        //设置垂直
        manager.setOrientation(OrientationHelper.VERTICAL);

        //设置适配器
        adapter = new MyRecyclerLineAdapter(getActivity(),list);
        //设置分割线
        recycleLine.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recycleLine.setAdapter(adapter);
        //设置点击监听
        adapter.setOnItemClickLitener(new MyRecyclerLineAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int positon) {
                list.get(index).ischeck=false;
                list.get(positon).ischeck=true;
                index=positon;
                //点击之后进行修改数据
                update(positon);
            }

            @Override
            public void onItemLongClick(View view, int position) {


            }
        });
    }


    private void update(int positon) {
        adapter.notifyDataSetChanged();//进行数据刷新
        childrenList.clear();//数据进行清空，放更新的数据
        //第一层children
        List<MyBean.RsBean.ChildrenBean> childrenBeen=list.get(positon).children;
        for (int i = 0; i < childrenBeen.size(); i++) {
            childrenBeen.get(i).isHeader = true;
            childrenList.add(childrenBeen.get(i));
            childrenList.addAll(childrenBeen.get(i).children);
        }
        contextAdapter.notifyDataSetChanged();//刷新内容展示
    }


}
