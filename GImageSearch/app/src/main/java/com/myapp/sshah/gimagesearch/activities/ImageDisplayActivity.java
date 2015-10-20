package com.myapp.sshah.gimagesearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapp.sshah.gimagesearch.R;
import com.myapp.sshah.gimagesearch.models.GoogleImage;
import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        GoogleImage image = (GoogleImage)getIntent().getSerializableExtra("imageObject");
        ImageView ivImage = (ImageView)findViewById(R.id.ivImageResult);
        TextView tvImageTitle = (TextView)findViewById(R.id.tvImageDisplayTitle);
        tvImageTitle.setText(Html.fromHtml(image.title));
        Picasso.with(this).load(image.fullUrl).into(ivImage);
    }
}
