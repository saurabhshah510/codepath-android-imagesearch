package com.myapp.sshah.gimagesearch.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sshah1 on 10/18/15.
 */
public class GoogleImage {
    public String thumbUrl;
    public String fullUrl;
    public String title;
    public int width;
    public int height;

    public GoogleImage(JSONObject jsonImageObject) throws JSONException{
        this.fullUrl = jsonImageObject.getString("url");
        this.thumbUrl = jsonImageObject.getString("tbUrl");
        this.height = jsonImageObject.getInt("height");
        this.width = jsonImageObject.getInt("width");
        this.title = jsonImageObject.getString("title");
    }

    public static ArrayList<GoogleImage> fromJSONArray(JSONArray jsonImageArray) throws JSONException{
        ArrayList<GoogleImage> imageResults = new ArrayList<>();
        for(int i = 0; i< jsonImageArray.length(); i++){
            JSONObject jsonImageObject = jsonImageArray.getJSONObject(i);
            GoogleImage image = new GoogleImage(jsonImageObject);
            imageResults.add(image);
        }
        return imageResults;
    }
}
