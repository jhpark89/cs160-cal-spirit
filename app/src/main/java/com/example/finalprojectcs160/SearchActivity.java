package com.example.finalprojectcs160;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ImageView category_im_1 = findViewById(R.id.category_im_1);
        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
                .apply(new RequestOptions().override(300, 300)).into(category_im_1);
        ImageView category_im_2 = findViewById(R.id.category_im_2);
        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
                .apply(new RequestOptions().override(300, 300)).into(category_im_2);
        ImageView category_im_3 = findViewById(R.id.category_im_3);
        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
                .apply(new RequestOptions().override(300, 300)).into(category_im_3);
        ImageView category_im_4 = findViewById(R.id.category_im_4);
        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
                .apply(new RequestOptions().override(300, 300)).into(category_im_4);
        ImageView category_im_5 = findViewById(R.id.category_im_5);
        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
                .apply(new RequestOptions().override(300, 300)).into(category_im_5);
        ImageView category_im_6 = findViewById(R.id.category_im_6);
        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
                .apply(new RequestOptions().override(300, 300)).into(category_im_6);
        ImageView category_im_7 = findViewById(R.id.category_im_7);
        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
                .apply(new RequestOptions().override(300, 300)).into(category_im_7);
        ImageView category_im_8 = findViewById(R.id.category_im_8);
        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
                .apply(new RequestOptions().override(300, 300)).into(category_im_8);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        // set the selected opt
        bottomNav.setSelectedItemId(R.id.botnav_search);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.botnav_explore:
                        startActivity(new Intent(getApplicationContext(), ExploreActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.botnav_search:
                        return true;
                    case R.id.botnav_favorites:
                        startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.botnav_profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        TextView causes = findViewById(R.id.causes_title);
        causes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, SearchCauses.class);
                startActivity(intent);
            }
        });
    }

}