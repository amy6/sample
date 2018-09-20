package com.example.mahima.autozang_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.location_icon)
    ImageView detectLocation;
    @BindView(R.id.location)
    EditText locationEditText;
    @BindView(R.id.call_button)
    Button callButton;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        callButton.setOnClickListener(this);
        detectLocation.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(this, setUpDummyData());
        recyclerView.setAdapter(adapter);
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

    private ArrayList<Service> setUpDummyData () {

        String imgUrl = "https://assets.vccircle.com/uploads/2017/09/car-1751750_1280.jpg";
        String name = "RajLakshmi Service Center";
        String location = "Tambaram, Chennai";
        String distance = "2.5km away";
        float rating = 4.3f;
        int reviewCount = 64;
        String days = "Monday to Saturday";
        String timings = "9am to 9pm";
        String chargeType = "Basic Standard Service Charge";
        double price = 500;
        String serviceType = "Pick and Drop Available";

        ArrayList<Service> serviceList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Service service = new Service(imgUrl, name, location, distance, rating, reviewCount, days, timings, chargeType, price, serviceType);
            serviceList.add(service);
        }
        return serviceList;
    }
}
