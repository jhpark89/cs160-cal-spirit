import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalprojectcs160.ExploreActivity;
import com.example.finalprojectcs160.R;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class LocationHandler {

    private String API_KEY;
    private Activity callingActivity;
    private double currLat;
    private double currLng;

    private String GEO_URL;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    public LocationHandler(String apiKey, Activity callingActivity) {
        API_KEY = apiKey;
        this.callingActivity = callingActivity;
        API_KEY = "AIzaSyAoN1rvQe4CUWkzjnhXnsOfYhwCX1S_zi0";
        GEO_URL = "https://maps.googleapis.com/maps/api/geocode/json";
    }

//    public String getDistance() {
//        if (ContextCompat.checkSelfPermission(
//                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
//        ) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                    ExploreActivity.this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    REQUEST_CODE_LOCATION_PERMISSION
//            );
//        } else {
//            getCurrentLocation();
//        }
//        return "";
//    }
//
//    private void requestAddressFromLatLng(double lat, double lng) {
//        String latlng = "?latlng=" + lat + "," + lng;
//        String url =  GEO_URL + latlng + "&key=" + API_KEY;
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        System.out.println(url);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Gson g = new Gson();
//                        Map<String, Object> resMap = g.fromJson(response, Map.class);
//                        System.out.println(resMap);
//                        ArrayList<Object> results = (ArrayList<Object>) resMap.get("results");
//                        Map<String, Object> result = g.fromJson(g.toJson(results.get(0)), Map.class);
//                        String address = (String) result.get("formatted_address");
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.println("That didn't work!");
//            }
//        });
//
//        queue.add(stringRequest);
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getCurrentLocation();
//            } else {
//                Toast.makeText(this, "Permission denied",  Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    private void getCurrentLocation() {
//        LocationRequest locationRequest = new LocationRequest();
//        locationRequest.setInterval(10000);
//        locationRequest.setFastestInterval(3000);
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//
//        LocationServices.getFusedLocationProviderClient(ExploreActivity.this)
//                .requestLocationUpdates(locationRequest, new LocationCallback(){
//
//                    @Override
//                    public void onLocationResult(LocationResult locationResult) {
//                        super.onLocationResult(locationResult);
//                        LocationServices.getFusedLocationProviderClient(ExploreActivity.this)
//                                .removeLocationUpdates(this);
//                        if (locationResult != null && locationResult.getLocations().size() > 0) {
//                            int latestLocationIndex = locationResult.getLocations().size() - 1;
//                            currLat =
//                                    locationResult.getLocations().get(latestLocationIndex).getLatitude();
//                            currLng =
//                                    locationResult.getLocations().get(latestLocationIndex).getLongitude();
//                            requestAddressFromLatLng(currLat, currLng);
//                        }
//                    }
//                }, Looper.getMainLooper());
//
//    }
}
