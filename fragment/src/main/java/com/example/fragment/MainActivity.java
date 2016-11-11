package com.example.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements View.OnClickListener{


    private Fragment1 f1;
    private Fragment2 f2;
    private Fragment3 f3;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        final Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        final Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        final Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment f = null;
                switch (position) {
                    case 0:
                        f = f1;
                        break;
                    case 1:
                        f = f2;
                        break;
                    case 2:
                        f = f3;
                        break;
                    default:
                        break;

                }
                return f;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        //viewpager的滑动监听
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            private void select(int position) {
                switch (position){
                    case 0:
                        btn1.setBackgroundColor(Color.RED);
                        btn2.setBackgroundColor(Color.WHITE);
                        btn3.setBackgroundColor(Color.WHITE);
                        break;
                    case 1:
                        btn1.setBackgroundColor(Color.WHITE);
                        btn3.setBackgroundColor(Color.WHITE);
                        btn2.setBackgroundColor(Color.BLUE);
                        break;
                    case 2:
                        btn1.setBackgroundColor(Color.WHITE);
                        btn2.setBackgroundColor(Color.WHITE);

                        btn3.setBackgroundColor(Color.BLACK);
                        break;
                    default:
                        break;
                }
            }


            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        select(position);
                        vp.setCurrentItem(position);
                        break;
                    case 1:
                        select(position);
                        vp.setCurrentItem(position);
                        break;
                    case 2:
                        select(position);
                        vp.setCurrentItem(position);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



    private void initFragment() {
        f1 = new Fragment1();
        f2 = new Fragment2();
        f3 = new Fragment3();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                vp.setCurrentItem(0);
                break;
            case R.id.btn2:
                vp.setCurrentItem(1);
                break;
            case R.id.btn3:
                vp.setCurrentItem(2);
                break;
            default:
                break;
        }
    }
}
