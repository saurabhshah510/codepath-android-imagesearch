package com.myapp.sshah.gimagesearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.myapp.sshah.gimagesearch.R;
import com.myapp.sshah.gimagesearch.adapters.ImageResultsAdapter;
import com.myapp.sshah.gimagesearch.api.GoogleImageClient;
import com.myapp.sshah.gimagesearch.models.GoogleImage;
import com.myapp.sshah.gimagesearch.models.Settings;
import com.myapp.sshah.gimagesearch.views.EndlessScrollListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements CallbackActivity {
    private EditText etSearchQuery;
    private GridView gvSearchResults;
    private ImageView ivSettingsIcon;
    private ArrayList<GoogleImage> imageResults;
    private ImageResultsAdapter aImageResults;
    private Settings settings;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        settings = new Settings();
        setupViews();
        imageResults = new ArrayList<>();
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvSearchResults.setAdapter(aImageResults);
    }

    private void setupViews(){
        etSearchQuery = (EditText)findViewById(R.id.etQuery);
        gvSearchResults = (GridView)findViewById(R.id.gvSearchResults);
        ivSettingsIcon = (ImageView)findViewById(R.id.ivSettingsIcon);
        final SearchActivity currentObj = this;
        gvSearchResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                GoogleImageClient.getSharedClient().fetchImages(currentObj, etSearchQuery.getText().toString(), page, settings);
                return true;
            }
        });
        gvSearchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
                GoogleImage image = imageResults.get(position);
                i.putExtra("imageObject", image);
                startActivity(i);
            }
        });
        ivSettingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                i.putExtra("settingsObject", settings);
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    public void onClickSearch(View view) {
        aImageResults.clear();
        String query = etSearchQuery.getText().toString();
        GoogleImageClient.getSharedClient().fetchImages(this, query, settings);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            this.settings = (Settings)data.getSerializableExtra("settingsObject");
            onClickSearch(null);
        }
    }

    @Override
    public void onFetchSuccess(ArrayList<GoogleImage> fetchedImageResults) {
        Log.i("DEBUG", "Adding " + fetchedImageResults.size() + " Photos");
        //Clear in case of new search
        aImageResults.addAll(fetchedImageResults);
    }

    @Override
    public void onFetchFailure() {

    }
}
