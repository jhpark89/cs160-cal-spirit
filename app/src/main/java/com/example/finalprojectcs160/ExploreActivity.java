package com.example.finalprojectcs160;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExploreActivity extends AppCompatActivity {

    private List<Business> businesses;
    public static final String BUSINESS = "com.example.prog01appv2.business";

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
        getDistance();
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
            String address = businesses.get(0).getAddress();

            images.add(thumbnail);
            names.add(name);
            dist.add(requestLatLngFromAddress(address) + " miles");
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


    public void onClickBusiness(View view) {
        // TODO: send business through an intent to the BusinessPageActivity
        // TODO: find the correct business from the view information (name or id or something)

        // currently hardcoding to go to Sophie's soaps
        Gson g = new Gson();
        Business bus = businesses.get(1);
        Intent intent = new Intent(this, BusinessPageActivity.class);
        intent.putExtra(BUSINESS, g.toJson(bus));
        // enum the source activity string later
        intent.putExtra(BusinessPageActivity.SOURCE_ACTIVITY, "explore");
        startActivity(intent);
    }



    /** API CODE HERE **/

    private String API_KEY = "AIzaSyAoN1rvQe4CUWkzjnhXnsOfYhwCX1S_zi0";
    private String GEO_URL = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private double currLat;
    private double currLng;
    private double businessLat;
    private double businessLng;

    public void onClickGetAddrTest(View view) {
        getDistance();
        requestLatLngFromAddress("2530+ridge+rd,+berkeley+ca");
    }

    public void getDistance() {
        System.out.println("Getting distance");
        if (ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    ExploreActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
        } else {
            getCurrentLocation();
        }

    }

    private double getDistanceFromLatLonInKm(double lat1, double lng1, double lat2, double lng2) {
        System.out.println("**************************");
        System.out.println(lat1);
        System.out.println(lng1);
        System.out.println(lat2);
        System.out.println(lng2);
        System.out.println("**************************");
        double R = 3958.8; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(lng2-lng1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        return d;
    }

    private double deg2rad(double deg) {
        return deg * (Math.PI/180.0);
    }

    private int res = -1;
    private int requestLatLngFromAddress(String addr) {
        String url = GEO_URL + "?address=" + addr + "&key=" + API_KEY;
        res = -1;
        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson g = new Gson();
                        Map<String, Object> resMap = g.fromJson(response, Map.class);
                        System.out.println(resMap);
                        ArrayList<Object> results = (ArrayList<Object>) resMap.get("results");
                        Map<String, Object> result = g.fromJson(g.toJson(results.get(0)), Map.class);
                        Map<String, Object> geometry = g.fromJson(g.toJson(result.get("geometry")), Map.class);
                        Map<String, Double> location = g.fromJson(g.toJson(geometry.get("location")), Map.class);
                        businessLat = location.get("lat");
                        businessLng = location.get("lng");

                        TextView currAddrTest = findViewById(R.id.currAddrTest);
                        res = (int) getDistanceFromLatLonInKm(currLat, currLng, businessLat, businessLng);
                        String text = "A neighborhood business is only " + res + " miles away!";
                        currAddrTest.setText(text);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });

        queue.add(stringRequest);

//        while (res == -1) {
//            ;
//        }
        return res;
    }

    private void requestAddressFromLatLng(double lat, double lng) {
        System.out.println("AAAA");
        String latlng = "?latlng=" + lat + "," + lng;
        String url =  GEO_URL + latlng + "&key=" + API_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson g = new Gson();
                        Map<String, Object> resMap = g.fromJson(response, Map.class);
                        System.out.println(resMap);
                        ArrayList<Object> results = (ArrayList<Object>) resMap.get("results");
                        Map<String, Object> result = g.fromJson(g.toJson(results.get(0)), Map.class);
                        String address = (String) result.get("formatted_address");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });

        queue.add(stringRequest);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
//                Toast.makeText(this, "Permission denied",  Toast.LENGTH_SHORT).show();
                getCurrentLocation();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        System.out.println("Made it here");
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(ExploreActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback(){

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(ExploreActivity.this)
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            currLat =
                                    locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            currLng =
                                    locationResult.getLocations().get(latestLocationIndex).getLongitude();
                            requestAddressFromLatLng(currLat, currLng);
                        }
                    }
                }, Looper.getMainLooper());

    }



}