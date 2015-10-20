package com.myapp.sshah.gimagesearch.api;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.myapp.sshah.gimagesearch.activities.CallbackActivity;
import com.myapp.sshah.gimagesearch.models.GoogleImage;
import com.myapp.sshah.gimagesearch.models.Settings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sshah1 on 10/17/15.
 */
public class GoogleImageClient {
    private static GoogleImageClient sharedClient;

    private GoogleImageClient(){}

    public static GoogleImageClient getSharedClient(){
        if(sharedClient == null){
            sharedClient = new GoogleImageClient();
        }
        return sharedClient;
    }

    public void fetchImages(CallbackActivity activity, String query, Settings settings){
        this.fetchImages(activity, query, 1, settings);
    }


    public void fetchImages(final CallbackActivity activity, String query, int page, Settings settings){
        AsyncHttpClient client = new AsyncHttpClient();
        int size = 8;
        StringBuilder url = new StringBuilder("https://ajax.googleapis.com/ajax/services/search/images?");
        try{
            url.append("q=" + URLEncoder.encode(query, "UTF-8"));
            url.append("&v=1.0");
            url.append("&rsz=" + size);
            url.append("&start=" + (page - 1) * size);
            url.append("&imgsz=" + settings.getSize());
        }catch (UnsupportedEncodingException ex){
            Log.i("ERROR", "Query couldnt be URL encoded");
            ex.printStackTrace();
            activity.onFetchFailure();
            return;
        }
        client.get(url.toString(), null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try{
                    JSONArray jsonImageArray = response.getJSONObject("responseData").getJSONArray("results");
                    activity.onFetchSuccess(GoogleImage.fromJSONArray(jsonImageArray));
                }catch(JSONException ex){
                    Log.i("ERROR", "Couldnt parse response JSON");
                    ex.printStackTrace();
                    activity.onFetchFailure();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("DEBUG", responseString);
                activity.onFetchFailure();
            }
        });
    }
}
