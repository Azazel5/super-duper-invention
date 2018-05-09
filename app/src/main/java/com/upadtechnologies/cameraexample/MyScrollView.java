package com.upadtechnologies.cameraexample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView
{
    private boolean enableScrolling = true;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isEnableScrolling() {
        return enableScrolling;
    }

    public void setEnableScrolling(boolean enableScrolling) {
        this.enableScrolling = enableScrolling;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        if(isEnableScrolling())
            return super.onInterceptTouchEvent(event);
        else
            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(isEnableScrolling())
            return super.onTouchEvent(event);
        else
            return false;
    }


}
