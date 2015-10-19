package com.myapp.sshah.gimagesearch.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.myapp.sshah.gimagesearch.models.GoogleImage;

import java.util.List;

/**
 * Created by sshah1 on 10/19/15.
 */
public class ImageResultsAdapter extends ArrayAdapter<GoogleImage> {
    public ImageResultsAdapter(Context context, List<GoogleImage> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
