package com.revosleap.blurlayout;


import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.revosleap.blurrylayout.BlurryLayout;

public class Blurry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurry);
    }

    @Override
    protected void onResume() {
        super.onResume();


        BlurryLayout blurLayout = findViewById(R.id.blurLayout);
        blurLayout.blurColor(Color.WHITE);
        blurLayout.blurOpacity(0.2f);
//        blurLayout.setDrawableBlur(getResources().getDrawable(R.drawable.medium_cover));
        blurLayout.setBitmapBlur(BitmapFactory.decodeResource(getResources(), R.drawable.medium_cover));
    }
}
