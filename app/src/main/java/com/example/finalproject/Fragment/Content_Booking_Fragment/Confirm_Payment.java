package com.example.finalproject.Fragment.Content_Booking_Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalproject.Fragment.Content_Discount_Fragment.DiscountAvailableAdapter;
import com.example.finalproject.R;

public class Confirm_Payment extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnPayment;
    private TextView chooseDiscount,tvHotel,tvRoom,tvAddress, tvCheckInCf, tvCheckOutCf, tvUserName,tvUserPhoneNumber,tvPrice,tvDiscount,tvTotalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);

        initUI();
        fetchDataIntent();

        chooseDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Confirm_Payment.this, ChooseDiscount.class);
                startActivity(intent);
            }
        });

    }

    private void fetchDataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            String hotelName = intent.getStringExtra("hotelName");
            String hotelAddress = intent.getStringExtra("hotelAddress");
            String roomName = intent.getStringExtra("roomName");
            long roomPrice = intent.getLongExtra("roomPrice", 0);
            int roomPriceInt = (int) roomPrice;
            String checkIn = intent.getStringExtra("checkIn");
            String checkOut = intent.getStringExtra("checkOut");
            String userName = intent.getStringExtra("userName");
            String userPhoneNumber = intent.getStringExtra("userPhoneNumber");

            tvHotel.setText(hotelName);
            tvAddress.setText(hotelAddress);
            tvRoom.setText(roomName);
            tvPrice.setText(roomPriceInt+"000đ");
            tvCheckInCf.setText(checkIn);
            tvCheckOutCf.setText(checkOut);
            tvUserName.setText(userName);
            tvUserPhoneNumber.setText(userPhoneNumber);


            // Now you have the data, you can use it as needed
            // For example, set the retrieved values to TextViews or display in logs
            Log.d("ConfirmPayment", "Hotel Name: " + hotelName);
            Log.d("ConfirmPayment", "Hotel Address: " + hotelAddress);
            // Set other values to TextViews or use them as needed
        }
    }

    private void initUI(){
        tvHotel = findViewById(R.id.tvHotel);
        tvRoom = findViewById(R.id.tvRoom);
        tvAddress = findViewById(R.id.tvAddress);
        tvCheckInCf = findViewById(R.id.tvCheckInCf);
        tvCheckOutCf = findViewById(R.id.tvCheckOutCf);
        tvUserName = findViewById(R.id.tvUserName);
        tvUserPhoneNumber = findViewById(R.id.tvUserPhoneNumber);
        tvPrice = findViewById(R.id.tvPrice);
        tvDiscount =findViewById(R.id.tvDiscount);
        tvTotalPrice =findViewById(R.id.tvTotalPrice);
        btnPayment = findViewById(R.id.btnPayment);
        chooseDiscount = findViewById(R.id.tvChooseDiscount);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}