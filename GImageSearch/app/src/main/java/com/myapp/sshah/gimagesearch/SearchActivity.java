package com.myapp.sshah.gimagesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    private EditText etSearchQuery;
    private GridView gvSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
    }

    private void setupViews(){
        etSearchQuery = (EditText)findViewById(R.id.etQuery);
        gvSearchResults = (GridView)findViewById(R.id.gvSearchResults);
    }

    public void onClickSearch(View view) {
        String query = etSearchQuery.getText().toString();
        Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
    }
}
