package com.example.mahima.autozang_sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        serviceList = new ArrayList<>();
        setUpDummyData();

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
        return  true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call_button:
                //intent to call
                break;
            case R.id.location_icon:
                //handle auto detect location
                break;
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
                for (int i = serviceList.size()-1; i > 0; i--) {
                    if (serviceList.get(i).getPrice() > 500) {
                        serviceList.remove(i);
                    }
                }
                break;
            case 1000:
                setUpDummyData();
                for (int i = serviceList.size()-1; i > 0; i--) {
                    if (serviceList.get(i).getPrice() > 1000) {
                        serviceList.remove(i);
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
