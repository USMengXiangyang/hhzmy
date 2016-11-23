package com.hhzmy.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hhzmy.fragment.ClassFragment;
import com.hhzmy.fragment.MyBuyFragment;
import com.hhzmy.fragment.ShoppingFragment;
import com.hhzmy.fragment.ShouyeFragment;

public class HomeActivity extends FragmentActivity implements View.OnClickListener {


    //    @BindView(R.id.lv)
//    LinearLayout lv;
    private ShouyeFragment shouye;
    private ClassFragment clas;
    private ShoppingFragment shop;
    private MyBuyFragment mbuy;
    //
    private FragmentManager manager;
    private FragmentTransaction tran;
    private RadioButton home;
    RadioGroup rg;
    private RadioButton type;
    private RadioButton car;
    private RadioButton mybuy;
    LinearLayout lv;
    private FragmentTransaction tran0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
//        ButterKnife.bind(this);
        lv = (LinearLayout) findViewById(R.id.lv);
        initButton();

        GetFragment();
        manager = getSupportFragmentManager();
        tran0 = manager.beginTransaction();
        tran0.add(R.id.fragment,shouye);
        tran0.commit();
        Intent intent = getIntent();
        String ismain = intent.getStringExtra("is");
        if(ismain!=null){
            FragmentTransaction tran3 = manager.beginTransaction();
            tran3.replace(R.id.fragment, shop);
            tran3.commit();
            car.setChecked(true);
            home.setChecked(false);
        }
    }

    private void initButton() {
        rg = (RadioGroup) findViewById(R.id.group);
        home = (RadioButton) findViewById(R.id.home);
        type = (RadioButton) findViewById(R.id.type);
        car = (RadioButton) findViewById(R.id.car);
        mybuy = (RadioButton) findViewById(R.id.mybuy);
        home.setOnClickListener(this);
        type.setOnClickListener(this);
        car.setOnClickListener(this);
        mybuy.setOnClickListener(this);
    }

    private void GetFragment() {
        shouye = new ShouyeFragment();
        clas = new ClassFragment();
        shop = new ShoppingFragment();
        mbuy = new MyBuyFragment();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home:
                Toast.makeText(HomeActivity.this, "-----", Toast.LENGTH_SHORT).show();
                manager = getSupportFragmentManager();
                tran = manager.beginTransaction();
                tran.replace(R.id.fragment, shouye);
                tran.commit();
                break;
            case R.id.type:

                FragmentTransaction tran2 = manager.beginTransaction();
                tran2.replace(R.id.fragment, clas);
                tran2.commit();
                break;
            case R.id.car:
                FragmentTransaction tran3 = manager.beginTransaction();
                tran3.replace(R.id.fragment, shop);
                tran3.commit();
                break;
            case R.id.mybuy:
                FragmentTransaction tran4 = manager.beginTransaction();
                tran4.replace(R.id.fragment, mbuy);
                tran4.commit();
                break;
        }
    }
}
