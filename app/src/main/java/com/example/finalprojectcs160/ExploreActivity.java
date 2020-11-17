package com.example.finalprojectcs160;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ExploreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

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
        bottomNav.setSelectedItemId(R.id.botnav_explore);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.botnav_explore:
                        return true;
                    case R.id.botnav_search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0, 0);
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


        createRecyclerViewClothing();
        createRecyclerViewBath();
        createRecyclerViewRestaurant();
    }

    private void createRecyclerViewClothing() {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> dist = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();

        images.add("https://i.imgur.com/ZcLLrkY.jpg");
        names.add("clothing");
        dist.add("10000 miles");

        images.add("https://i.imgur.com/ZcLLrkY.jpg");
        names.add("clothing 2");
        dist.add("1 miles");
        images.add("https://i.imgur.com/ZcLLrkY.jpg");
        names.add("clothing 3");
        dist.add("1 miles");
        RecyclerView clothing = findViewById(R.id.recycler_view_clothing);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, names, dist, images);
        clothing.setAdapter(adapter);
        clothing.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));

    }
    private void createRecyclerViewBath() {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> dist = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();

        images.add("https://i.imgur.com/ZcLLrkY.jpg");
        names.add("bath");
        dist.add("10000 miles");

        images.add("https://i.imgur.com/ZcLLrkY.jpg");
        names.add("bath 2");
        dist.add("1 miles");
        images.add("https://i.imgur.com/ZcLLrkY.jpg");
        names.add("bath 3");
        dist.add("1 miles");
        RecyclerView clothing = findViewById(R.id.recycler_view_bath);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, names, dist, images);
        clothing.setAdapter(adapter);
        clothing.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));

    }
    private void createRecyclerViewRestaurant() {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> dist = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();

        images.add("https://i.imgur.com/ZcLLrkY.jpg");
        names.add("top dog");
        dist.add("10000 miles");

        images.add("https://i.imgur.com/ZcLLrkY.jpg");
        names.add("la burrita");
        dist.add("1 miles");
        images.add("https://i.imgur.com/ZcLLrkY.jpg");
        names.add("crossroads");
        dist.add("1 miles");
        RecyclerView clothing = findViewById(R.id.recycler_view_restaurant);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, names, dist, images);
        clothing.setAdapter(adapter);
        clothing.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));

    }


}