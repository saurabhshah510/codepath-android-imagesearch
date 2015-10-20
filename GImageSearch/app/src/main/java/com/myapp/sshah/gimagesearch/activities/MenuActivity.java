package com.myapp.sshah.gimagesearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.myapp.sshah.gimagesearch.R;
import com.myapp.sshah.gimagesearch.models.Settings;

public class MenuActivity extends AppCompatActivity {

    RadioGroup rgSize;
    Button btSettingsDone;
    private MenuActivity currentActivity;
    //Size maps
    private Settings settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        settings = (Settings)getIntent().getSerializableExtra("settingsObject");
        currentActivity = this;
        setupViews();
    }

    private void setupViews(){
        btSettingsDone = (Button)findViewById(R.id.btSettingsDone);
        rgSize = (RadioGroup) findViewById(R.id.rgSize);
        rgSize.check(sizeStringToId(settings.getSize()));
        rgSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                settings.setSize(sizeIdToString(checkedId));
            }
        });
        btSettingsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("settingsObject", settings);
                currentActivity.setResult(RESULT_OK, data);
                currentActivity.finish();
            }
        });
    }

    private String sizeIdToString(int id){
        if(id == R.id.rbSizeSmall) return "small";
        if(id == R.id.rbSizeMedium) return "medium";
        if(id == R.id.rbSizeLarge) return "large";
        return "xlarge";
    }

    private int sizeStringToId(String size){
        if(size.equals("small")) return R.id.rbSizeSmall;
        if(size.equals("medium")) return R.id.rbSizeMedium;
        if(size.equals("large")) return R.id.rbSizeLarge;
        return R.id.rbSizeXLarge;

    }
}
