package com.example.mahima.autozang_sample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    @BindView(R.id.location_icon)
    ImageView detectLocation;
    @BindView(R.id.location)
    EditText locationEditText;
    @BindView(R.id.call_button)
    Button callButton;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList<Service> serviceList;
    private CustomRecyclerViewAdapter adapter;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left);
        }

        serviceList = new ArrayList<>();
        setUpDummyData();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        callButton.setOnClickListener(this);
        detectLocation.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new CustomRecyclerViewAdapter(this, serviceList);
        recyclerView.setAdapter(adapter);

        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                //handle filters
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call_button:
                //intent to call
                break;
            case R.id.location_icon:
                //handle auto detect location
                if (locationManager != null) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        Log.d(MainActivity.class.getSimpleName(), "No Permissions");
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        return;
                    } else {
                        List<String> providers = locationManager.getProviders(true);
                        Location location = null;
                        for (String provider : providers) {
                            Location l = locationManager.getLastKnownLocation(provider);
                            if (l == null) {
                                continue;
                            }
                            if (location == null || l.getAccuracy() < location.getAccuracy()) {
                                location = l;
                            }
                        }
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            Log.d(MainActivity.class.getSimpleName(), "Lat : " + latitude + "\tLong: " + longitude);
                            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                            try {
                                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                String cityName = addresses.get(0).getAddressLine(0);
                                locationEditText.setText(cityName != null ? cityName : "");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(MainActivity.class.getSimpleName(), "Requesting Permissions");
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Please enable GPS to autodetect location", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void setUpDummyData () {

        String imgUrl = "https://assets.vccircle.com/uploads/2017/09/car-1751750_1280.jpg";
        String name = "RajLakshmi Service Center";
        String location = "Tambaram, Chennai";
        String distance = "2.5km away";
        float rating = 4.3f;
        int reviewCount = 64;
        String days = "Monday to Saturday";
        String timings = "9am to 9pm";
        String chargeType = "Basic Standard Service Charge";
        double price = 200;
        String serviceType = "Pick and Drop Available";

        for (int i = 0; i < 10; i++) {
            Service service = new Service(imgUrl, name, location, distance, rating, reviewCount, days, timings, chargeType, price * (i + 1), serviceType);
            serviceList.add(service);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        String price = sharedPreferences.getString(s, "-1");
        if (serviceList != null) {
            serviceList.clear();
            adapter.notifyDataSetChanged();
        }

        switch (Integer.parseInt(price)) {
            case -1:
                Log.d(MainActivity.class.getSimpleName(), "-1");
                setUpDummyData();
                break;

            case 500:
                setUpDummyData();
                Iterator<Service> iterator = serviceList.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().getPrice() > 500) {
                        iterator.remove();
                    }
                }
                break;
            case 1000:
                setUpDummyData();
                iterator = serviceList.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().getPrice() <= 500) {
                        iterator.remove();
                    }
                }
                iterator = serviceList.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().getPrice() > 1000) {
                        iterator.remove();
                    }
                }
                break;

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
}
