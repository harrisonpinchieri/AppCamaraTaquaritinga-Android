package com.camarataquaritinga.projeto.camarataquaritinga;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class StreamingActivity extends AppCompatActivity {


    VideoView videoView;

    String videoURL ="https://player.jmvstream.com/6TkFNb3J9igpAQDq1LLvkmrCVKKv0h";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaming);


        videoView = (VideoView) findViewById(R.id.videoViewStreamingID);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse(videoURL);


       videoView.setMediaController(mediaController);


        videoView.setVideoURI(uri);
      //  videoView.requestFocus();
        videoView.start();


    }
}
