package com.upadtechnologies.cameraexample;


import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    private static final String VID_LINK = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4";
    private VideoView mVideoView;
    private MyMediaController mController;
    private RelativeLayout.LayoutParams paramsNotFullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mVideoView = findViewById(R.id.video_view);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mController = new MyMediaController(VideoActivity.this, VideoActivity.this);
                        mVideoView.setMediaController(mController);
                        mController.setAnchorView(mVideoView);
                    }
                });
            }
        });
        mVideoView.setVideoPath(VID_LINK);
        mVideoView.start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            paramsNotFullscreen=(RelativeLayout.LayoutParams)mVideoView.getLayoutParams();
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(paramsNotFullscreen);
            params.setMargins(0, 0, 0, 0);
            params.height=ViewGroup.LayoutParams.MATCH_PARENT;
            params.width=ViewGroup.LayoutParams.MATCH_PARENT;
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            mVideoView.setLayoutParams(params);

        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            mVideoView.setLayoutParams(paramsNotFullscreen);
        }
    }

}
