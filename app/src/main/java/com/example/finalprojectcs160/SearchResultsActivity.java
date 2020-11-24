package com.example.finalprojectcs160;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {
    private List<Business> businesses;
    public static final String query_string = "com.example.finalprojectcs160.SearchReesultsActivity.query_string";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results2);
        Intent intent = getIntent();
        String query = intent.getStringExtra(SearchActivity.query_string);
        if (query == null) {
            query = intent.getStringExtra(SearchResultsActivity.query_string);
        }
        createBottomNavigationBar();
        SearchView searchbar = findViewById(R.id.searchbar_result);
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(SearchResultsActivity.this, SearchResultsActivity.class);
                intent.putExtra(query_string, query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        int causes = intent.getIntExtra(SearchCausesActivity.causesnumber, -1);
        JSONResourceReader reader = new JSONResourceReader(getResources(), R.raw.business_data);
        Type listBusinesses = new TypeToken<ArrayList<Business>>() {}.getType();
        businesses = reader.constructUsingGson(listBusinesses);
        List<Integer> matching_ids = new ArrayList<>();
        System.out.println(businesses);
        if (causes == -1) {
            for (int i = 0; i < businesses.size(); i++) {
                Business bus = businesses.get(i);
                for (String tag : bus.getTags()) {
                    System.out.println(tag);
                    if (tag.contains(query)) {
                        matching_ids.add(i);
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < businesses.size(); i++) {
                Business bus = businesses.get(i);
                System.out.println("WE HERE BOIS");

                if (bus.getCauses()[causes]) {
                    matching_ids.add(i);
                }
            }
        }
        for (int i = 0; i < matching_ids.size(); i++) {
            System.out.print(businesses.get(matching_ids.get(i)).getName());
            System.out.println("TEST");
        }
        TextView num_shops = findViewById(R.id.num_shops);
        if (matching_ids.size() < 2) {
            num_shops.setText(matching_ids.size() + " shop found.");
        } else {
            num_shops.setText(matching_ids.size() + " shops found.");

        }
        createRecyclerViewResults(matching_ids);
        System.out.println(R.drawable.business_stockimg_14karat);
    }
    private void createRecyclerViewResults(List<Integer> ids) {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> dist = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        ArrayList<Boolean> isURL = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            final int temp = i;
            String thumbnail = businesses.get(ids.get(i)).getImg();
            String name = businesses.get(ids.get(i)).getName();
            System.out.println("results " + name);
            images.add(thumbnail);
            names.add(name);
            dist.add("100 miles");
            isURL.add(false);
        }

        RecyclerView results = findViewById(R.id.recycler_search_res);
        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(this, names, dist, images, isURL);
        results.setAdapter(adapter);
        results.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
    }
    private void createBottomNavigationBar() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
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
}