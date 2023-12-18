package com.example.finalproject.Fragment.Content_Booking_Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rcvServiceHotel;
    private ServiceHotelAdapter serviceHotelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

        rcvServiceHotel = findViewById(R.id.rcvServiceHotel);
        serviceHotelAdapter = new ServiceHotelAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        rcvServiceHotel.setLayoutManager(linearLayoutManager);

        serviceHotelAdapter.setData(getListService());
        rcvServiceHotel.setAdapter(serviceHotelAdapter);
    }
    private List<ServiceHotel> getListService(){
        List<ServiceHotel> listServiceHotel = new ArrayList<>();

        listServiceHotel.add(new ServiceHotel(true,true,true,true,true,true));

        return listServiceHotel;
    }
}