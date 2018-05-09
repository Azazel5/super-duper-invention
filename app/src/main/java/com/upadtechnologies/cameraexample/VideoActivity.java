package com.upadtechnologies.cameraexample;


import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    private static final String VID_LINK = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4";
    private VideoView mVideoView;
    private MyMediaController mController;
    private RelativeLayout.LayoutParams paramsNotFullscreen;
    private MyScrollView mScrollView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mScrollView = findViewById(R.id.scroll_view);
        mVideoView = findViewById(R.id.video_view);
        mEditText = findViewById(R.id.random_text_view);
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
            mEditText.getText().clear();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
            mScrollView.setEnableScrolling(false);
            paramsNotFullscreen=(RelativeLayout.LayoutParams)mVideoView.getLayoutParams();
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(paramsNotFullscreen);
            params.setMargins(0, 0, 0, 0);
            params.height=ViewGroup.LayoutParams.MATCH_PARENT;
            params.width=ViewGroup.LayoutParams.MATCH_PARENT;
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            mVideoView.setLayoutParams(params);

        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            mEditText.setText(R.string.about_chrome_cast);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().show();
            mScrollView.setEnableScrolling(true);
            mVideoView.setLayoutParams(paramsNotFullscreen);
        }
    }
}
