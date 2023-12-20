package com.example.finalproject.Fragment.Content_Booking_Fragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.Hotel;
import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.HotelAdapter;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class SearchHotel extends AppCompatActivity {

    private Toolbar toolbar;
    private HotelAdapter hotelAdapter;
    private RecyclerView rcvSearchResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        initUI();


        hotelAdapter = new HotelAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcvSearchResult.setLayoutManager(linearLayoutManager);
        hotelAdapter.setData(getListHotel());
        rcvSearchResult.setAdapter(hotelAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            String type = intent.getStringExtra("type");
            String checkIn = intent.getStringExtra("checkIn");
            String checkOut = intent.getStringExtra("checkOut");
            getSupportActionBar().setSubtitle(type + " | " + checkIn + " - " + checkOut);

            // Bây giờ bạn có thể sử dụng giá trị checkIn và checkOut theo cách bạn muốn
        }
    }
    private void initUI(){
        rcvSearchResult = findViewById(R.id.rcvSearchResult);
        toolbar = findViewById(R.id.toolbarSearchResult);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private List<Hotel> getListHotel(){
        List<Hotel> listHotel = new ArrayList<>();


        listHotel.add(new Hotel("KS1","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));
        listHotel.add(new Hotel("KS2","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));
        listHotel.add(new Hotel("KS3","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));
        listHotel.add(new Hotel("KS4","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));

        return listHotel;
    }

}