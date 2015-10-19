package com.myapp.sshah.gimagesearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.myapp.sshah.gimagesearch.R;
import com.myapp.sshah.gimagesearch.adapters.ImageResultsAdapter;
import com.myapp.sshah.gimagesearch.api.GoogleImageClient;
import com.myapp.sshah.gimagesearch.models.GoogleImage;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements CallbackActivity {
    private EditText etSearchQuery;
    private GridView gvSearchResults;
    private ArrayList<GoogleImage> imageResults;
    private ImageResultsAdapter aImageResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
        imageResults = new ArrayList<>();
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvSearchResults.setAdapter(aImageResults);
    }

    private void setupViews(){
        etSearchQuery = (EditText)findViewById(R.id.etQuery);
        gvSearchResults = (GridView)findViewById(R.id.gvSearchResults);
    }

    public void onClickSearch(View view) {
        String query = etSearchQuery.getText().toString();
        GoogleImageClient.getSharedClient().fetchImages(this, query);
    }

    @Override
    public void onFetchSuccess(ArrayList<GoogleImage> fetchedImageResults) {
        Log.i("DEBUG", "Adding " + fetchedImageResults.size() + " Photos");
        //Clear in case of new search
        imageResults.clear();
        aImageResults.addAll(fetchedImageResults);
    }

    @Override
    public void onFetchFailure() {

    }
}
