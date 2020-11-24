package com.example.finalprojectcs160;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class BusinessPageActivity extends AppCompatActivity {

    private Business business;
    private String source_activity;
    public static final String SOURCE_ACTIVITY = "com.example.prog01appv2.source_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_page);

        Intent intent = getIntent();
        source_activity = intent.getStringExtra(SOURCE_ACTIVITY);

        Gson g = new Gson();
        business = g.fromJson(intent.getStringExtra(ExploreActivity.BUSINESS), Business.class);

        TextView name = findViewById(R.id.bus_title);
        name.setText(business.getName());

        TextView desc = findViewById(R.id.bus_desc);
        desc.setText(business.getDesc());

        TextView info = findViewById(R.id.bus_info);
        info.setText("$$ ");

        TextView tags = findViewById(R.id.bus_tags);
        tags.setText("Sustainable, BIPOC-Owned");

        TextView times = findViewById(R.id.bus_times);
        times.setText("9AM - 6PM");
    }

    public void onClickBack(View view) {
        Intent intent;
        if (source_activity.equals("explore")) {
            intent = new Intent(this, ExploreActivity.class);
        } else {
            intent = new Intent(this, SearchActivity.class);
        }
        startActivity(intent);
    }
}