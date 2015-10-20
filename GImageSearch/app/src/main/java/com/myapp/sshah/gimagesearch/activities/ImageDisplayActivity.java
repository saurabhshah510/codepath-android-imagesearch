package com.myapp.sshah.gimagesearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.myapp.sshah.gimagesearch.R;
import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        String url = getIntent().getStringExtra("url");
        ImageView ivImage = (ImageView)findViewById(R.id.ivImageResult);
        Picasso.with(this).load(url).into(ivImage);
    }
}
