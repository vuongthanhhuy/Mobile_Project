package com.example.finalproject.Fragment.Content_Booking_Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class HotelDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rcvServiceHotel;
    private ServiceHotelAdapter serviceHotelAdapter;
    private Button btnChooseRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        btnChooseRoom = findViewById(R.id.btnChooseRoom);
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
        serviceHotelAdapter = new ServiceHotelAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        rcvServiceHotel.setLayoutManager(linearLayoutManager);

        serviceHotelAdapter.setData(getListService());
        rcvServiceHotel.setAdapter(serviceHotelAdapter);

        btnChooseRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListRoom.class);
                startActivity(intent);
            }
        });
    }
    private List<ServiceHotelModel> getListService(){
        List<ServiceHotelModel> listServiceHotel = new ArrayList<>();

        listServiceHotel.add(new ServiceHotelModel(true,true,true,true,true,true));

        return listServiceHotel;
    }
}