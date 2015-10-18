package com.myapp.sshah.gimagesearch.activities;

import com.myapp.sshah.gimagesearch.models.GoogleImage;

import java.util.ArrayList;

/**
 * Created by sshah1 on 10/17/15.
 */
public interface CallbackActivity {
    public void onFetchSuccess(ArrayList<GoogleImage> imageResults);
    public void onFetchFailure();
}
