package com.example.finalproject.Fragment.Content_Account_Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.finalproject.Fragment.Content_Booking_Fragment.ListRoomAdapter;
import com.example.finalproject.Fragment.Content_Booking_Fragment.ListRoomModel;
import com.example.finalproject.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryBooking extends AppCompatActivity {
    private String userID;
    private Toolbar toolbar;
    private RecyclerView rcvHistoryBooking;
    private HistoryBookingAdapter historyBookingAdapter;
    private int completedTasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_booking);

        initUI();
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        fetchData();

    }
    private void initUI(){
        rcvHistoryBooking = findViewById(R.id.rcvHistoryBooking);
        toolbar = (Toolbar) findViewById(R.id.toolbarHistoryBooking);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

        historyBookingAdapter = new HistoryBookingAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvHistoryBooking.setLayoutManager(linearLayoutManager);
    }
    private void fetchData(){
        if (userID != null && !userID.isEmpty()) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("booking")
                    .whereEqualTo("userID", userID)  // Lọc theo trường userID
                    .get()
                    .addOnCompleteListener(task -> {
                        List<HistoryBookingModel> historyBooking = new ArrayList<>();
                        completedTasks = 0;

                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                String bookingDate = document.getString("bookingDate");
                                String bookingID = document.getId(); // Lấy ID của document
                                String hotelID = document.getString("hotelID");
                                String roomID = document.getString("roomID");
                                long totalPrice = document.getLong("totalPrice");
                                int totalPriceInt = (int) totalPrice;

                                db.collection("hotel")
                                        .document(hotelID)
                                        .get()
                                        .addOnSuccessListener(hotelDocument -> {
                                            if (hotelDocument.exists()) {
                                                String hotelName = hotelDocument.getString("hotelName");

                                                db.collection("hotel")
                                                        .document(hotelID)
                                                        .collection("room")
                                                        .document(roomID)
                                                        .get()
                                                        .addOnSuccessListener(roomDocument -> {
                                                            if (roomDocument.exists()) {
                                                                String roomName = roomDocument.getString("roomName");
                                                                historyBooking.add(new HistoryBookingModel(bookingDate, bookingID, hotelName, roomName, totalPriceInt));

                                                                // Tăng biến completedTasks sau mỗi lần hoàn thành một công việc bất đồng bộ
                                                                completedTasks++;

                                                                // Kiểm tra xem tất cả các công việc đã hoàn tất chưa
                                                                if (completedTasks == task.getResult().size()) {
                                                                    // Nếu đã hoàn tất, thì gọi setData và setAdapter
                                                                    historyBookingAdapter.setData(historyBooking);
                                                                    rcvHistoryBooking.setAdapter(historyBookingAdapter);
                                                                }
                                                            }
                                                        });
                                            }
                                        });
                            }
                        }
                    });
        }
    }
}