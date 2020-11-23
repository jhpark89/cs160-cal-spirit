package com.example.finalprojectcs160;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {
    private List<Business> businesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Intent intent = getIntent();
        String query = intent.getStringExtra(SearchActivity.query_string);
        JSONResourceReader reader = new JSONResourceReader(getResources(), R.raw.business_data);
        Type listBusinesses = new TypeToken<ArrayList<Business>>() {}.getType();
        businesses = reader.constructUsingGson(listBusinesses);
        List<Integer> matching_ids = new ArrayList<>();
        System.out.println(businesses);
        for (int i = 0; i < businesses.size(); i++) {
            Business bus = businesses.get(i);
                for (String tag: bus.getTags()) {
                    System.out.println(tag);
                    if (tag.contains(query)) {
                        matching_ids.add(i);
                        break;
                    }
                }
            }
        for (int i = 0; i < matching_ids.size(); i++) {
            System.out.print(businesses.get(matching_ids.get(i)).getName());
            System.out.println("TEST");
        }
        TextView num_shops = findViewById(R.id.num_shops);
        if (matching_ids.size() < 2) {

        }
        createRecyclerViewResults(matching_ids);
        System.out.println(R.drawable.business_stockimg_soap);
    }
    private void createRecyclerViewResults(List<Integer> ids) {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> dist = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        ArrayList<Boolean> isURL = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
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
}