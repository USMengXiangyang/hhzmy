package com.hhzmy.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import com.hhzmy.twicelogin.TwiceLogin;

public class MainActivity extends AppCompatActivity {

    private int count = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ImageView gpsImg = (ImageView) findViewById(R.id.gpsImg);
        handler.postDelayed(runnable, 2000);
        super.onCreate(savedInstanceState);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            TwiceLogin.setLogin(MainActivity.this, true);
            finish();
        }
    };
}
