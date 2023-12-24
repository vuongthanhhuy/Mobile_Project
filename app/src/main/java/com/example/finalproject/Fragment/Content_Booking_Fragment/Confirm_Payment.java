package com.example.finalproject.Fragment.Content_Booking_Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Fragment.Content_Discount_Fragment.DiscountAvailableAdapter;
import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Confirm_Payment extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnPayment;
    private ImageView imgTypeBooking;
    private TextView chooseDiscount,tvHotel,tvRoom,tvAddress, tvCheckInCf, tvCheckOutCf, tvUserName,tvUserPhoneNumber,tvPrice,tvDiscount,tvTotalPrice;
    private RadioButton rdbCash, rdbVNPay;
    private int priceRoom;
    private String hotelID,roomID,userID, hotelName, hotelAddress, roomName;
    private boolean typeBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);

        initUI();
        fetchDataIntent();
        eventClick();


    }

    private void fetchDataIntent() {
        Intent intent = getIntent();
        hotelID = intent.getStringExtra("hotelID");
        roomID = intent.getStringExtra("roomID");
        userID = intent.getStringExtra("userID");
        hotelName = intent.getStringExtra("hotelName");
        hotelAddress = intent.getStringExtra("hotelAddress");
        typeBooking = intent.getBooleanExtra("typeBooking", false);
        roomName = intent.getStringExtra("roomName");
        long roomPrice = intent.getLongExtra("roomPrice", 0);
        int roomPriceInt = (int) roomPrice;
        priceRoom = roomPriceInt;
        String checkIn = intent.getStringExtra("checkIn");
        String checkOut = intent.getStringExtra("checkOut");
        String userName = intent.getStringExtra("userName");
        String userPhoneNumber = intent.getStringExtra("userPhoneNumber");

        tvHotel.setText(hotelName);
        tvAddress.setText(hotelAddress);
        tvRoom.setText(roomName);
        String priceString = priceRoom+"đ";
        tvPrice.setText(priceString);
        tvTotalPrice.setText(priceString);
        tvCheckInCf.setText(checkIn);
        tvCheckOutCf.setText(checkOut);
        tvUserName.setText(userName);
        tvUserPhoneNumber.setText(userPhoneNumber);
        if(typeBooking){
            imgTypeBooking.setImageResource(R.drawable.bgday);
        }else{
            imgTypeBooking.setImageResource(R.drawable.bgnight);
        }
        chooseDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Confirm_Payment.this, ChooseDiscount.class);
                intent.putExtra("price", roomPriceInt);
                startActivityForResult(intent, 222);
            }
        });

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
        imgTypeBooking = findViewById(R.id.imgTypeBooking);
        rdbCash = findViewById(R.id.cash);
        rdbCash.setChecked(true);
        rdbVNPay = findViewById(R.id.vnpay);
        btnPayment = findViewById(R.id.btnPayment);
        chooseDiscount = findViewById(R.id.tvChooseDiscount);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int discount = 0;
        String discountString = "-"+discount+"đ";
        tvDiscount.setText(discountString);

    }

    private void eventClick(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdbCash.isChecked()){
                    addBookingInformation();
                    Toast.makeText(Confirm_Payment.this, "Đặt phòng thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Confirm_Payment.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 222 && resultCode == RESULT_OK && data != null) {
            String promotionID = data.getStringExtra("promotionID");
            int discountPercent = data.getIntExtra("discountPercent", 0);
            int percent = (discountPercent * priceRoom) / 100;
            String discount = "-"+percent+"đ";
            tvDiscount.setText(discount);
            int totalPrice = priceRoom - percent;
            String totalPriceString = totalPrice + "đ";
            tvTotalPrice.setText(totalPriceString);
        }
    }

    private void addBookingInformation() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String pattern = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        // Lấy dữ liệu từ Intent hoặc từ các thành phần UI khác
        String checkIn = tvCheckInCf.getText().toString();
        String checkOut = tvCheckOutCf.getText().toString();
        String totalPrice = tvTotalPrice.getText().toString();
        String priceWithoutCurrency = totalPrice.replaceAll("[^\\d]", "");
        int totalPriceInt = Integer.parseInt(priceWithoutCurrency);
        String currentDate = dateFormat.format(date);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference bookingRef = db.collection("booking").document();

        // Lấy bookingID từ documentReference
        String bookingID = bookingRef.getId();

        // Thêm bookingID vào thông tin đặt phòng
        Map<String, Object> bookingInfo = new HashMap<>();
        bookingInfo.put("bookingDate", currentDate);
        bookingInfo.put("bookingID", bookingID);
        bookingInfo.put("roomID", roomID);
        bookingInfo.put("hotelID", hotelID);
        bookingInfo.put("userID", userID);
        bookingInfo.put("startDate", checkIn);
        bookingInfo.put("endDate", checkOut);
        bookingInfo.put("totalPrice", totalPriceInt);
        // Thêm thông tin vào collection "booking"
        bookingRef.set(bookingInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Confirm_Payment", "Booking information added with ID: " + bookingID);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Confirm_Payment", "Error adding booking information", e);
                    }
                });
    }
}