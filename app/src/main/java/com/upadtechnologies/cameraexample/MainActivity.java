package com.upadtechnologies.cameraexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_IMAGE_REQUEST_CODE = 0;
    private ImageView mImageView;
    private Button capturePic, captureVid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.imgPreview);
        capturePic = findViewById(R.id.capture_picture);
        captureVid = findViewById(R.id.capture_vid);

        capturePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    captureImage();
            }
        });

        captureVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
            }
        });

    }
    private void captureImage()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            mImageView.setVisibility(View.VISIBLE);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(photo);
        }
    }

}
