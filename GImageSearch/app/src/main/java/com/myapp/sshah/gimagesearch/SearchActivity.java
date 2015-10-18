package com.myapp.sshah.gimagesearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.myapp.sshah.gimagesearch.activity.CallbackActivity;
import com.myapp.sshah.gimagesearch.api.GoogleImageClient;
import com.myapp.sshah.gimagesearch.model.GoogleImage;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements CallbackActivity {
    private EditText etSearchQuery;
    private GridView gvSearchResults;
    private ArrayList<GoogleImage> imageResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        imageResults = new ArrayList<>();
        setupViews();
    }

    private void setupViews(){
        etSearchQuery = (EditText)findViewById(R.id.etQuery);
        gvSearchResults = (GridView)findViewById(R.id.gvSearchResults);
    }

    public void onClickSearch(View view) {
        String query = etSearchQuery.getText().toString();
        Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        GoogleImageClient.getSharedClient().fetchImages(this, query);
    }

    @Override
    public void onFetchSuccess(ArrayList<GoogleImage> imageResults) {
        Log.i("DEBUG", "Adding " + imageResults.size() + " Photos");
    }

    @Override
    public void onFetchFailure() {

    }
}
