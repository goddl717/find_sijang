package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AddressActivity extends AppCompatActivity {
    private WebView browser;
    private SharedPreferences sharedPreferences;
    String result_data;
    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processDATA(String data) {

            Bundle extra = new Bundle();
            Intent intent = new Intent();
            result_data = data.substring(7,data.length()).replaceAll("\\(.*?\\)", ""); // 우편번호 제거;
            extra.putString("data", result_data);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("배달 주소 설정");


        browser = (WebView) findViewById(R.id.webView);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

        browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                browser.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });
        browser.loadUrl("http://findsijang.dothome.co.kr/");

    }

    @Override
    public void onBackPressed() {
        if(browser.canGoBack()){
            browser.goBack();
        }else{
            super.onBackPressed();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AddressActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("address",result_data);
        editor.apply();
    }
}