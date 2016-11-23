package com.hhzmy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.hhzmy.adaper.My_Grid_Adapter;
import com.hhzmy.adaper.My_List_adapter;
import com.hhzmy.adaper.My_shouye_adapter;
import com.hhzmy.adaper.My_shouye_grid_ada;
import com.hhzmy.adaper.My_shouye_recyc_adapter;
import com.hhzmy.bean.JsonBean;
import com.hhzmy.dataneturl.OkHttp;
import com.hhzmy.test.ErweimaActivity;
import com.hhzmy.test.OtherActivity;
import com.hhzmy.test.R;
import com.hhzmy.webview.DetailsActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by asus on 2016/11/8.
 */
public class ShouyeFragment extends Fragment {
    private My_shouye_adapter my_shouye_ada;
    private ViewPager shouye_vp;
    private JsonBean datat;
    private String path = "http://image1.suning.cn";
    private String pathjson = "http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga";


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 先去获取到当前条目索引
            int currentItem = shouye_vp.getCurrentItem();
            // 条目索引+1
            currentItem++;
            // 重新设置给viewPager
            shouye_vp.setCurrentItem(currentItem);
            // 再调用发延时消息的方法
            sendDelayMessage();
        }
    };
    private View view;
    private GridView home_grid;
    private My_Grid_Adapter my_home_grid;
    private LayoutInflater mInflater;
    private LinearLayout mGallery;
    private String imgdata;
    //二维码扫描
    private ImageView home_scan;
    private List<JsonBean.DataBean.TagBean> data;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.shouye_fragment, null);
        mInflater = LayoutInflater.from(getActivity());
        initData();
        sendDelayMessage();
        initView();
        home_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ErweimaActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initView() {
        shouye_vp = (ViewPager) view.findViewById(R.id.shouye_vp);
        home_grid = (GridView) view.findViewById(R.id.home_gri);
        home_scan = (ImageView) view.findViewById(R.id.home_scan);
    }

    private void initData() {

        OkHttp.getAsync(pathjson, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                System.out.println("数据加载成功。。。。");
                Gson gson = new Gson();
                datat = gson.fromJson(result, JsonBean.class);
                //viewpager数据
                data = datat.getData().get(0).getTag();
                my_shouye_ada = new My_shouye_adapter(path, getActivity(), handler, data);
                shouye_vp.setAdapter(my_shouye_ada);
                //grideview数据
                List<JsonBean.DataBean.TagBean> data_grid = datat.getData().get(1).getTag();
                my_home_grid = new My_Grid_Adapter(path, getActivity(), data_grid);
                home_grid.setAdapter(my_home_grid);
                List<JsonBean.DataBean.TagBean> gallery = datat.getData().get(2).getTag();
                //titleImage
                ImageView title_img = (ImageView) view.findViewById(R.id.title_img);
                ImageLoader.getInstance().displayImage(path + gallery.get(0).getPicUrl(), title_img);
                //grallery图片
                initGrallery(gallery);
                //title2图片
                ImageView img2 = (ImageView) view.findViewById(R.id.title2_img);
                List<JsonBean.DataBean.TagBean> grid_item = datat.getData().get(4).getTag();
                ImageLoader.getInstance().displayImage(path + grid_item.get(0).getPicUrl(), img2);
                initgrideData();
                //title3图片
                ImageView img3 = (ImageView) view.findViewById(R.id.title3_img);
                List<JsonBean.DataBean.TagBean> dataimg = datat.getData().get(9).getTag();
                ImageLoader.getInstance().displayImage(path+dataimg.get(0).getPicUrl(),img3);
                //
                List<JsonBean.DataBean.TagBean> my_img = datat.getData().get(10).getTag();
                //母婴专区图片展示
                initMyImg(my_img);
                //母婴专区2
                List<JsonBean.DataBean.TagBean> my_img2 = datat.getData().get(11).getTag();
                initMying2(my_img2);
                //主题特卖
                List<JsonBean.DataBean.TagBean> my_img4 = datat.getData().get(13).getTag();
                ImageView title_img4 = (ImageView) view.findViewById(R.id.title_img4);
                ImageLoader.getInstance().displayImage(path+my_img4.get(0).getPicUrl(),title_img4);
                //洋货超值购
                List<JsonBean.DataBean.TagBean> data_ing1 = datat.getData().get(14).getTag();
                ImageView title_img5 = (ImageView) view.findViewById(R.id.title_img5);
                ImageLoader.getInstance().displayImage(path+data_ing1.get(0).getPicUrl(),title_img5);
                //recycleview条目
                List<JsonBean.DataBean.TagBean> data_recyc1 = datat.getData().get(15).getTag();
                initRecyc1(data_recyc1);
                //聚优聚省聚慧
                List<JsonBean.DataBean.TagBean> data_img2 = datat.getData().get(16).getTag();
                ImageView title_img6 = (ImageView) view.findViewById(R.id.title_img6);
                ImageLoader.getInstance().displayImage(path+data_img2.get(0).getPicUrl(),title_img6);
                List<JsonBean.DataBean.TagBean> data_recyc2 = datat.getData().get(17).getTag();
                initRecyc2(data_recyc2);
                //加量不加价
                List<JsonBean.DataBean.TagBean> data_img3 = datat.getData().get(18).getTag();
                ImageView title_img7 = (ImageView) view.findViewById(R.id.title_img7);
                ImageLoader.getInstance().displayImage(path+data_img3.get(0).getPicUrl(),title_img7);
                List<JsonBean.DataBean.TagBean> data_recyc3 = datat.getData().get(19).getTag();
                initRecyc3(data_recyc3);
                //省钱
                List<JsonBean.DataBean.TagBean> data_img4 = datat.getData().get(20).getTag();
                ImageView title_img8 = (ImageView) view.findViewById(R.id.title_img8);
                ImageLoader.getInstance().displayImage(path+data_img4.get(0).getPicUrl(),title_img8);
                List<JsonBean.DataBean.TagBean> data_recyc4 = datat.getData().get(21).getTag();
                initRecyc4(data_recyc4);
                //辣妈拼团
                List<JsonBean.DataBean.TagBean> data_img5 = datat.getData().get(23).getTag();
                ImageView title_img9 = (ImageView) view.findViewById(R.id.title_img9);
                ImageLoader.getInstance().displayImage(path+data_img5.get(0).getPicUrl(),title_img9);
                //辣妈拼团商品
                initListData();
            }
        });
    }

    private void initListData() {
        ListView list = (ListView) view.findViewById(R.id.shouye_list);
        List<String> string = new ArrayList<>();
        Log.e("=-=-=-","555666");
        List<JsonBean.DataBean.TagBean> data_tag = new ArrayList<>();
        for(int i=24;i<34;i++){
            data_tag = datat.getData().get(i).getTag();
            String data_tag_str = data_tag.get(0).getPicUrl();
            Log.e("data_tag_str",data_tag_str);
            if(!data_tag_str.equals("")){
                string.add(data_tag_str);
            }
        }
        Log.e("data_tag",string.size()+"");
        My_List_adapter  my_List_adapter = new My_List_adapter(getActivity(),string,path);
        list.setAdapter(my_List_adapter);

    }

    private void initRecyc4(List<JsonBean.DataBean.TagBean> data_recyc4) {
        RecyclerView recyc1 = (RecyclerView) view.findViewById(R.id.id_recyclerview4);
        LinearLayoutManager linnerlayout = new LinearLayoutManager(getActivity());
        linnerlayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyc1.setLayoutManager(linnerlayout);
        Log.e("-=-=-=",data_recyc4.size()+"");
        My_shouye_recyc_adapter my_shouye_recyc_adapter = new My_shouye_recyc_adapter(getActivity(),data_recyc4,path);
        recyc1.setAdapter(my_shouye_recyc_adapter);
    }

    private void initRecyc3(List<JsonBean.DataBean.TagBean> data_recyc3) {
        RecyclerView recyc1 = (RecyclerView) view.findViewById(R.id.id_recyclerview3);
        LinearLayoutManager linnerlayout = new LinearLayoutManager(getActivity());
        linnerlayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyc1.setLayoutManager(linnerlayout);
        Log.e("-=-=-=",data_recyc3.size()+"");
        My_shouye_recyc_adapter my_shouye_recyc_adapter = new My_shouye_recyc_adapter(getActivity(),data_recyc3,path);
        recyc1.setAdapter(my_shouye_recyc_adapter);

    }

    private void initRecyc2(List<JsonBean.DataBean.TagBean> data_recyc2) {
        RecyclerView recyc1 = (RecyclerView) view.findViewById(R.id.id_recyclerview2);
        LinearLayoutManager linnerlayout = new LinearLayoutManager(getActivity());
        linnerlayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyc1.setLayoutManager(linnerlayout);
        Log.e("-=-=-=",data_recyc2.size()+"");
        My_shouye_recyc_adapter my_shouye_recyc_adapter = new My_shouye_recyc_adapter(getActivity(),data_recyc2,path);
        recyc1.setAdapter(my_shouye_recyc_adapter);

    }

    private void initRecyc1(List<JsonBean.DataBean.TagBean> data_recyc1) {
        RecyclerView recyc1 = (RecyclerView) view.findViewById(R.id.id_recyclerview1);
        LinearLayoutManager linnerlayout = new LinearLayoutManager(getActivity());
        linnerlayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyc1.setLayoutManager(linnerlayout);
        Log.e("-=-=-=",data_recyc1.size()+"");
        My_shouye_recyc_adapter my_shouye_recyc_adapter = new My_shouye_recyc_adapter(getActivity(),data_recyc1,path);
        recyc1.setAdapter(my_shouye_recyc_adapter);

    }

    private void initMying2(List<JsonBean.DataBean.TagBean> my_img2) {
        ImageView img = new ImageView(getActivity());
        ImageView img3 = (ImageView) view.findViewById(R.id.my_img3);
        ImageView img4 = (ImageView) view.findViewById(R.id.my_img4);
        ImageView img5 = (ImageView) view.findViewById(R.id.my_img5);
        ImageView img6 = (ImageView) view.findViewById(R.id.my_img6);
        ImageLoader.getInstance().displayImage(path+my_img2.get(0).getPicUrl(),img3);
        ImageLoader.getInstance().displayImage(path+my_img2.get(1).getPicUrl(),img4);
        ImageLoader.getInstance().displayImage(path+my_img2.get(2).getPicUrl(),img5);
        ImageLoader.getInstance().displayImage(path+my_img2.get(3).getPicUrl(),img6);


    }

    private void initMyImg(List<JsonBean.DataBean.TagBean> my_img) {
        ImageView img1 = (ImageView) view.findViewById(R.id.my_img1);
        ImageView img2= (ImageView) view.findViewById(R.id.my_img2);
        ImageLoader.getInstance().displayImage(path+my_img.get(0).getPicUrl(),img1);
        ImageLoader.getInstance().displayImage(path+my_img.get(1).getPicUrl(),img2);
    }

    //傲娇品牌gridview
    private void initgrideData() {
        GridView gride = (GridView) view.findViewById(R.id.shouye_grid2);
        List<String> string = new ArrayList<>();
        List<JsonBean.DataBean.TagBean> shouye_grid = new ArrayList<>();
        //首页grideview
        int x= 0;
        for (int i = 5; i < 8; i++) {
           shouye_grid = datat.getData().get(i).getTag();
            for(int n =0;n<2;n++){
                imgdata = shouye_grid.get(n).getPicUrl();
                string.add(imgdata);
            }

        }
        Log.e("=====",string.size()+"");
        My_shouye_grid_ada my_shou_grid_ada = new My_shouye_grid_ada(path,string,getActivity());
        gride.setAdapter(my_shou_grid_ada);
    }


    //grallery图片
    private void initGrallery(final List<JsonBean.DataBean.TagBean> gallery) {
        mGallery = (LinearLayout) view.findViewById(R.id.id_gallery);

        for (int i = 1; i < gallery.size(); i++) {

            View view = mInflater.inflate(R.layout.shouye_index_gallery_item,
                    mGallery, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);
            final int finalI = i;
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),DetailsActivity.class);
//                    intent.putExtra("linkurl",gallery.get(finalI).getLinkUrl());
                    startActivity(intent);
                }
            });
            ImageLoader.getInstance().displayImage(path + gallery.get(i).getPicUrl(), img);
            mGallery.addView(view);
        }

    }


    private void sendDelayMessage() {
        handler.sendEmptyMessageDelayed(0, 5000);
    }
}
