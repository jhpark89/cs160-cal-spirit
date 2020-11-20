package com.example.finalprojectcs160;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

    }
}