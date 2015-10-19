package com.myapp.sshah.gimagesearch.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapp.sshah.gimagesearch.R;
import com.myapp.sshah.gimagesearch.models.GoogleImage;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sshah1 on 10/19/15.
 */
public class ImageResultsAdapter extends ArrayAdapter<GoogleImage> {
    public ImageResultsAdapter(Context context, List<GoogleImage> objects) {
        super(context, R.layout.item_image_result, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GoogleImage imageInfo = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
        }
        ImageView ivImage = (ImageView)convertView.findViewById(R.id.ivImage);
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvImageTitle);
        //Clear out existing image
        ivImage.setImageResource(0);
        tvTitle.setText(Html.fromHtml(imageInfo.title));
        Picasso.with(getContext()).load(imageInfo.thumbUrl).into(ivImage);
        return convertView;
    }
}
