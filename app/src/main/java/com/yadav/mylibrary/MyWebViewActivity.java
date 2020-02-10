package com.yadav.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewActivity extends AppCompatActivity {
    private static final String TAG = "MyWebViewActivity";

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);

        String url ="";
        try{
             url= getIntent().getStringExtra("url");
        }catch (NullPointerException e){
            Log.d(TAG, "onCreate: No Intent Recieved.");
        }
        webView= findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }else {
        super.onBackPressed();
        }
    }
}
