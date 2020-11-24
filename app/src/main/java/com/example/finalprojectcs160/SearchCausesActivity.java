package com.example.finalprojectcs160;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchCausesActivity extends AppCompatActivity {
    public static final String causesnumber = "com.example.finalprojectcs160.SearchCausesActivity.causesnumber";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_causes2);
        createBottomNavigationBar();
        TextView categories = findViewById(R.id.categories_title2);
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchCausesActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        ImageView[] buttons = new ImageView[9];
        buttons[0] = findViewById(R.id.cause_bipoc);
        buttons[1] = findViewById(R.id.causes_coop);
        buttons[2] = findViewById(R.id.causes_fair);
        buttons[3] = findViewById(R.id.causes_ethical);
        buttons[4] = findViewById(R.id.causes_sustainable);
        buttons[5] = findViewById(R.id.causes_lgbtq);
        buttons[6] = findViewById(R.id.causes_accessible);
        buttons[7] = findViewById(R.id.causes_bcorp);
        buttons[8] = findViewById(R.id.causes_give);
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchCausesActivity.this, SearchResultsActivity.class);
                intent.putExtra(causesnumber, 0);
                startActivity(intent);
            }
        });
        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchCausesActivity.this, SearchResultsActivity.class);
                intent.putExtra(causesnumber, 1);
                startActivity(intent);
            }
        });
        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchCausesActivity.this, SearchResultsActivity.class);
                intent.putExtra(causesnumber, 2);
                startActivity(intent);
            }
        });
        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchCausesActivity.this, SearchResultsActivity.class);
                intent.putExtra(causesnumber, 3);
                startActivity(intent);
            }
        });
        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchCausesActivity.this, SearchResultsActivity.class);
                intent.putExtra(causesnumber, 4);
                startActivity(intent);
            }
        });
        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchCausesActivity.this, SearchResultsActivity.class);
                intent.putExtra(causesnumber, 5);
                startActivity(intent);
            }
        });
        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchCausesActivity.this, SearchResultsActivity.class);
                intent.putExtra(causesnumber, 6);
                startActivity(intent);
            }
        });
        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchCausesActivity.this, SearchResultsActivity.class);
                intent.putExtra(causesnumber, 7);
                startActivity(intent);
            }
        });
        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchCausesActivity.this, SearchResultsActivity.class);
                intent.putExtra(causesnumber, 8);
                startActivity(intent);
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