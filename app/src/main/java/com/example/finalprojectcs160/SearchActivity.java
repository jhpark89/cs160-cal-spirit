package com.example.finalprojectcs160;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {
    final static public String query_string = "com.example.finalprojectcs160.SearchActivity.query_string";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ImageView category_im_1 = findViewById(R.id.category_im_1);
        category_im_1.setImageResource(R.drawable.groceries);
//        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
//                .apply(new RequestOptions().override(300, 300)).into(category_im_1);
        ImageView category_im_2 = findViewById(R.id.category_im_2);
        category_im_2.setImageResource(R.drawable.clothing);
//        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
//                .apply(new RequestOptions().override(300, 300)).into(category_im_2);
        ImageView category_im_3 = findViewById(R.id.category_im_3);
        category_im_3.setImageResource(R.drawable.bathbody);
//        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
//                .apply(new RequestOptions().override(300, 300)).into(category_im_3);
        ImageView category_im_4 = findViewById(R.id.category_im_4);
        category_im_4.setImageResource(R.drawable.restaurants);
//        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
//                .apply(new RequestOptions().override(300, 300)).into(category_im_4);
        ImageView category_im_5 = findViewById(R.id.category_im_5);
        category_im_5.setImageResource(R.drawable.services);
//        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
//                .apply(new RequestOptions().override(300, 300)).into(category_im_5);
        ImageView category_im_6 = findViewById(R.id.category_im_6);
        category_im_6.setImageResource(R.drawable.other);
//        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
//                .apply(new RequestOptions().override(300, 300)).into(category_im_6);
//        ImageView category_im_7 = findViewById(R.id.category_im_7);
//        category_im_1.setImageResource(R.drawable.groceries);
//        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
//                .apply(new RequestOptions().override(300, 300)).into(category_im_7);
//        ImageView category_im_8 = findViewById(R.id.category_im_8);
//        category_im_1.setImageResource(R.drawable.groceries);
//        Glide.with(this).load("https://i.imgur.com/ZcLLrkY.jpg")
//                .apply(new RequestOptions().override(300, 300)).into(category_im_8);

        createBottomNavigationBar();
        SearchView searchbar = findViewById(R.id.searchbar_search);
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(SearchActivity.this, SearchResultsActivity.class);
                intent.putExtra(query_string, query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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
                Intent intent = new Intent(SearchActivity.this, SearchCausesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView= (SearchView) menu.findItem(R.id.searchbar_explore).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        return true;
    }
}