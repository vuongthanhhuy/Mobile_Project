package com.example.finalproject.Fragment.Content_Booking_Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy.Content_Payment_Policy;
import com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy.Content_Privacy_Policy;
import com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy.Content_Service_Agreement;
import com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy.CustomPagerAdapter;
import com.example.finalproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ListRoom extends AppCompatActivity {
    private RecyclerView rcvListRoom;
    private ListRoomAdapter listRoomAdapter;
    private Toolbar toolbar;
    private LinearLayout linearLayout;
    private TextView tvCheckIn, tvCheckOut;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);
        context = ListRoom.this;
        rcvListRoom = findViewById(R.id.rcvListRoom);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        linearLayout = findViewById(R.id.selectTimeLayout);
        tvCheckIn = findViewById(R.id.tvcheckIn);
        tvCheckOut = findViewById(R.id.tvcheckOut);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });
        listRoomAdapter = new ListRoomAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcvListRoom.setLayoutManager(linearLayoutManager);

        listRoomAdapter.setData(getListRoom());
        rcvListRoom.setAdapter(listRoomAdapter);


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListRoom.this, Dialog_Select_Night_Day.class);
                startActivityForResult(intent, 111);

            }
        });


    }

    private List<ListRoomModel> getListRoom(){
        List<ListRoomModel> listServiceHotel = new ArrayList<>();

        listServiceHotel.add(new ListRoomModel(500000,"Deluxe Room",true,true,true,true, true));
        listServiceHotel.add(new ListRoomModel(500000,"Deluxe Room",true,true,true, true, true));
        listServiceHotel.add(new ListRoomModel(500000,"Deluxe Room",true,true,true, true, true));
        listServiceHotel.add(new ListRoomModel(500000,"Deluxe Room",true,true,true, true, true));

        return listServiceHotel;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == RESULT_OK && data != null) {
            // Receive Intent data
            String checkIn = data.getStringExtra("checkIn");
            String checkOut = data.getStringExtra("checkOut");
            tvCheckIn.setText(checkIn);
            tvCheckOut.setText(checkOut);

        }
    }
}