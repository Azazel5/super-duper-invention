package com.upadtechnologies.cameraexample;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;

public class MyMediaController extends MediaController{

    private AppCompatActivity mAppCompatActivity;

    public MyMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMediaController(Context context, AppCompatActivity appCompatActivity) {
        super(context);
        mAppCompatActivity = appCompatActivity;
    }

    @Override
    public void setAnchorView(View view)
    {
        super.setAnchorView(view);
        Button landscapeButton = new Button(getContext());
        landscapeButton.getBackground().setAlpha(0);
        landscapeButton.setBackgroundResource(R.drawable.landscape_button);
        landscapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mAppCompatActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            }
        });

        Button portraitButton = new Button(getContext());
        portraitButton.getBackground().setAlpha(0);
        portraitButton.setBackgroundResource(R.drawable.portrait_button);
        portraitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
              mAppCompatActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            }
        });

        FrameLayout.LayoutParams paramsLand = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramsLand.gravity = Gravity.RIGHT;
        addView(landscapeButton, paramsLand);

        FrameLayout.LayoutParams paramsPort = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramsPort.gravity = Gravity.LEFT;
        addView(portraitButton, paramsPort);

    }
}
