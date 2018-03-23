package com.camarataquaritinga.projeto.camarataquaritinga;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.VideoView;

public class StreamingActivity extends AppCompatActivity {


    VideoView videoView;


    String videoURL ="https://player.jmvstream.com/6TkFNb3J9igpAQDq1LLvkmrCVKKv0h";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_streaming);




        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl("https://player.jmvstream.com/6TkFNb3J9igpAQDq1LLvkmrCVKKv0h");

        //habilita javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }
}
