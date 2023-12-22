package com.example.finalproject.Fragment.Content_Booking_Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HotelDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rcvServiceHotel;
    private ServiceHotelAdapter serviceHotelAdapter;
    private Button btnChooseRoom;
    private TextView tvHotelName, tvHotelAddress, tvHotelIntro, tvCheckInNight, tvCheckInDay,tvHotelPrice;
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
    private String hotelID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        initUI();
        Intent intent = getIntent();

        // Lấy dữ liệu từ Intent
        hotelID = intent.getStringExtra("hotelID");
        Log.d("id", hotelID);
        String hotelName = intent.getStringExtra("hotelName");
        String hotelAddress = intent.getStringExtra("hotelAddress");
        boolean[] hotelServiceArray = intent.getBooleanArrayExtra("hotelServiceArray");
        String hotelIntro = intent.getStringExtra("hotelIntro");
        String checkInTimeNight = intent.getStringExtra("checkInNight");
        String checkInTimeDay = intent.getStringExtra("checkInDay");
        long hotelPrice = intent.getLongExtra("hotelPrice", 0);
        int hotelPriceInt = (int) hotelPrice;

        String[] hotelImgRoomArray = intent.getStringArrayExtra("hotelImgRoomArray");


        tvHotelName.setText(hotelName);
        tvHotelAddress.setText(hotelAddress);
        tvHotelIntro.setText(hotelIntro);
        tvHotelPrice.setText(hotelPriceInt+"000đ");
        String checkInNight = "Từ " + checkInTimeNight + " tới 12:00";
        String checkInDay = "Từ " + checkInTimeDay + " tới 12:00";
        tvCheckInNight.setText(checkInNight);
        tvCheckInDay.setText(checkInDay);



        serviceHotelAdapter = new ServiceHotelAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        rcvServiceHotel.setLayoutManager(linearLayoutManager);
        List<ServiceHotelModel> listServiceHotel = getListService(hotelServiceArray);
        serviceHotelAdapter.setData(listServiceHotel);
        rcvServiceHotel.setAdapter(serviceHotelAdapter);


        if (hotelImgRoomArray != null && hotelImgRoomArray.length > 0) {
            // Sử dụng Glide để tải và hiển thị hình ảnh vào các ImageView
            Glide.with(this).load(hotelImgRoomArray[0]).into(imageView1);
            Glide.with(this).load(hotelImgRoomArray[1]).into(imageView2);
            Glide.with(this).load(hotelImgRoomArray[2]).into(imageView3);
            Glide.with(this).load(hotelImgRoomArray[3]).into(imageView4);
            Glide.with(this).load(hotelImgRoomArray[4]).into(imageView5);
        }
        btnChooseRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putDataToRoom();
            }
        });

    }

    private void initUI(){
        tvHotelName = findViewById(R.id.tvHotelName);
        tvHotelAddress = findViewById(R.id.tvHotelAddress);
        tvHotelIntro = findViewById(R.id.tvHotelIntro);
        tvCheckInNight = findViewById(R.id.tvCheckInNight);
        tvCheckInDay = findViewById(R.id.tvCheckInDay);
        tvHotelPrice = findViewById(R.id.tvHotelPrice);
        imageView1 = findViewById(R.id.imgView1);
        imageView2 = findViewById(R.id.imgView2);
        imageView3 = findViewById(R.id.imgView3);
        imageView4 = findViewById(R.id.imgView4);
        imageView5 = findViewById(R.id.imgView5);

        btnChooseRoom = findViewById(R.id.btnChooseRoom);
        rcvServiceHotel = findViewById(R.id.rcvServiceHotel);

        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });
    }
    private List<ServiceHotelModel> getListService(boolean[] hotelServiceArray) {
        List<ServiceHotelModel> listServiceHotel = new ArrayList<>();
        if (hotelServiceArray != null && hotelServiceArray.length > 0) {
            boolean hasService1 = hotelServiceArray[0];
            boolean hasService2 = hotelServiceArray[1];
            boolean hasService3 = hotelServiceArray[2];
            boolean hasService4 = hotelServiceArray[3];
            boolean hasService5 = hotelServiceArray[4];
            boolean hasService6 = hotelServiceArray[5];
            listServiceHotel.add(new ServiceHotelModel(hasService1, hasService2, hasService3, hasService4, hasService5, hasService6));

        }

        return listServiceHotel;
    }


    private void putDataToRoom(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference hotelCollection = db.collection("hotel");
        hotelCollection.whereEqualTo("hotelID", hotelID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot hotelDocument : task.getResult()) {
                        String hotelId = hotelDocument.getId();
                        CollectionReference roomCollection = hotelCollection.document(hotelId).collection("room");
                        roomCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot roomDocument : task.getResult()) {
                                        String hotelID = hotelId;
                                        String roomName = roomDocument.getString("roomName");
                                        List<String> roomImage = (List<String>) roomDocument.get("roomImage");
                                        if (roomImage != null) {
                                            String[] roomImageArray = new String[roomImage.size()];
                                            for (int i = 0; i < roomImage.size(); i++) {
                                                roomImageArray[i] = roomImage.get(i);
                                            }

                                            List<Boolean> roomService = (List<Boolean>) roomDocument.get("roomAmenities");
                                            if (roomService != null) {
                                                boolean[] roomServiceArray = new boolean[roomService.size()];
                                                for (int i = 0; i < roomService.size(); i++) {
                                                    roomServiceArray[i] = roomService.get(i);
                                                }
                                                Long roomPrice = roomDocument.getLong("roomPrice");
                                                boolean roomStatus = roomDocument.getBoolean("roomStatus");

                                                Intent intent = new Intent(HotelDetails.this, ListRoom.class);
                                                intent.putExtra("hotelID", hotelID);
                                                intent.putExtra("roomName", roomName);
                                                intent.putExtra("roomImage", roomImageArray);
                                                intent.putExtra("roomService", roomServiceArray);
                                                intent.putExtra("roomPrice", roomPrice);
                                                intent.putExtra("roomStatus", roomStatus);
                                                startActivity(intent);
                                            }
                                        }
                                    }
                                } else {
                                    Log.d("FirestoreQuery", "Error getting documents from room collection", task.getException());
                                }
                            }
                        });
                    }
                } else {
                    Log.d("FirestoreQuery", "Error getting documents from hotel collection", task.getException());
                }
            }
        });



    }
}