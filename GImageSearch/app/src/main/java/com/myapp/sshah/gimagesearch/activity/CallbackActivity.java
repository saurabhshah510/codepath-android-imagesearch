package com.myapp.sshah.gimagesearch.activity;

import com.myapp.sshah.gimagesearch.model.GoogleImage;

import java.util.ArrayList;

/**
 * Created by sshah1 on 10/17/15.
 */
public interface CallbackActivity {
    public void onFetchSuccess(ArrayList<GoogleImage> imageResults);
    public void onFetchFailure();
}
