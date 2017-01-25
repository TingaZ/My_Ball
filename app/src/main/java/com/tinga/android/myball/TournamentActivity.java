package com.tinga.android.myball;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class TournamentActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_tournament);



        final VideoView videoview = (VideoView) findViewById(R.id.videoview);
         Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.alone_in_strager_place_sipho_gumede);
                videoview.setVideoURI(uri);


                MediaController mediaController = new
                        MediaController(TournamentActivity.this);
                mediaController.setAnchorView(videoview);
                videoview.setMediaController(mediaController);

                videoview.start();

    }

}
