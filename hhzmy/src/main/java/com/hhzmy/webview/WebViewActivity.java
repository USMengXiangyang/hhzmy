package com.hhzmy.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hhzmy.test.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        wv = (WebView) findViewById(R.id.webview);
        wv.setWebViewClient(new WebViewClient() {

        });
        Intent intent = getIntent();
        wv.loadUrl(intent.getStringExtra("imgurl"));
        wv.loadUrl(intent.getStringExtra("picurl"));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(wv.canGoBack()){
                wv.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
