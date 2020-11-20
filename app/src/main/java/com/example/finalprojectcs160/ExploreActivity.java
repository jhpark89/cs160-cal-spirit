package com.example.finalprojectcs160;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends AppCompatActivity {

    private List<Business> businesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        // Create a JSON reader to get data from local database and turn it into list of businesses
        JSONResourceReader reader = new JSONResourceReader(getResources(), R.raw.business_data);
        Type listBusinesses = new TypeToken<ArrayList<Business>>() {}.getType();
        businesses = reader.constructUsingGson(listBusinesses);

        createBottomNavigationBar();
        createRecyclerViewClothing();
        createRecyclerViewBath();
        createRecyclerViewRestaurant();
    }

    private void createBottomNavigationBar() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
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
    }

    private void createRecyclerViewClothing() {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> dist = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        ArrayList<Boolean> isURL = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String thumbnail = businesses.get(0).getImg();
            String name = businesses.get(0).getName();

            images.add(thumbnail);
            names.add(name);
            dist.add("100 miles");
            isURL.add(false);
        }

        RecyclerView clothing = findViewById(R.id.recycler_view_clothing);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, names, dist, images, isURL);
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