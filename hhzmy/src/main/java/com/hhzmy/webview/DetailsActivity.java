package com.hhzmy.webview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzmy.fragment.DetailsFragment;
import com.hhzmy.fragment.GoodsFragment;
import com.hhzmy.fragment.ShoppingFragment;
import com.hhzmy.pay.PayDemoActivity;
import com.hhzmy.test.HomeActivity;
import com.hhzmy.test.R;

public class DetailsActivity extends FragmentActivity implements View.OnClickListener{

    private ImageView details_img_back;
    private TextView details_goos;
    private TextView details;
    private FragmentManager manager;
    private FragmentTransaction tran0;
    private GoodsFragment goods;
    private FragmentTransaction tran1;
    private DetailsFragment detailsFragment;
    private FragmentTransaction tran2;
    private Button go_price;
    private ShoppingFragment shoppingFragment;
    private FragmentTransaction tran5;
    private Button go_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        iniyFragment();
        manager = getSupportFragmentManager();
        tran0 = manager.beginTransaction();
        tran0.add(R.id.fragment_car,goods);
        tran0.commit();
        initView();
    }

    private void iniyFragment() {
        goods = new GoodsFragment();
        detailsFragment = new DetailsFragment();
        shoppingFragment = new ShoppingFragment();
    }

    private void initView() {
        details_img_back = (ImageView) findViewById(R.id.details_img_back);
        details_img_back.setOnClickListener(this);
        details_goos = (TextView) findViewById(R.id.details_goods);
        details_goos.setOnClickListener(this);
        details = (TextView) findViewById(R.id.details_details);
        details.setOnClickListener(this);
        go_price = (Button) findViewById(R.id.go_price);
        go_price.setOnClickListener(this);
        go_car = (Button)findViewById(R.id.go_car);
        go_car.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.details_img_back:
                finish();
                break;
            case R.id.details_goods:
                manager = getSupportFragmentManager();
                tran1 = manager.beginTransaction();
                tran1.replace(R.id.fragment_car,goods);
                tran1.commit();
                details_goos.setTextColor(Color.YELLOW);
                details.setTextColor(Color.BLACK);
                break;
            case R.id.details_details:
                tran2 = manager.beginTransaction();
                tran2.replace(R.id.fragment_car,detailsFragment);
                tran2.commit();
                details.setTextColor(Color.YELLOW);
                details_goos.setTextColor(Color.BLACK);
                break;
            case R.id.go_price:
                Intent intent = new Intent(this,PayDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.go_car:
                finish();
                Intent intent1 = new Intent(DetailsActivity.this, HomeActivity.class);
                intent1.putExtra("is","FFF");
                startActivity(intent1);
                break;
        }
    }
}
