package com.example.mahima.autozang_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
}
