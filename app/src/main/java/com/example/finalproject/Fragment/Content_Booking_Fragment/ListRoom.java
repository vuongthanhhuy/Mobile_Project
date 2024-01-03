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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy.Content_Payment_Policy;
import com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy.Content_Privacy_Policy;
import com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy.Content_Service_Agreement;
import com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy.CustomPagerAdapter;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListRoom extends AppCompatActivity {
    private RecyclerView rcvListRoom;
    private ListRoomAdapter listRoomAdapter;
    private Toolbar toolbar;
    private LinearLayout linearLayout;
    private TextView tvCheckIn, tvCheckOut;
    private Context context;
    public String hotelID, checkIn,checkOut,checkInBefore,checkOutBefore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);
        initUI();
        clickEvent();
        listRoomAdapter = new ListRoomAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvListRoom.setLayoutManager(linearLayoutManager);
        listRoomAdapter.updateCheckInOut(checkIn, checkOut);
        listRoomAdapter.updateCheckInOut(tvCheckIn.getText().toString(), tvCheckOut.getText().toString());

        Intent intent = getIntent();
        hotelID = intent.getStringExtra("hotelID");
        checkInBefore = intent.getStringExtra("checkIn");
        checkOutBefore = intent.getStringExtra("checkOut");
        tvCheckIn.setText(checkInBefore);
        tvCheckOut.setText(checkOutBefore);

        getListRoom();

    }

    private void clickEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListRoom.this, Dialog_Select_Night_Day.class);
                startActivityForResult(intent, 111);

            }
        });
    }

    private void getListRoom() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference hotelCollection = db.collection("hotel");

        CollectionReference roomCollection = hotelCollection.document(hotelID).collection("room");
        roomCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<ListRoomModel> listRoom= new ArrayList<>();
                    ArrayList<SlideModel> imageList = new ArrayList<>();
                    for (QueryDocumentSnapshot roomDocument : task.getResult()) {
                        String roomID = roomDocument.getString("roomID");
                        CollectionReference bookingCollection = db.collection("booking");
                        bookingCollection.whereEqualTo("roomID", roomID)
                                .get()
                                .addOnCompleteListener(task2 -> {
                                    if (task2.isSuccessful()) {
                                        Log.d("roomID",roomID);

                                        int count = task2.getResult().size();
                                        int count2 = 0;
                                        for (QueryDocumentSnapshot bookingDocument : task2.getResult()) {
                                            // Logic kiểm tra sự sẵn có của khách sạn
                                            String startDateBooking = bookingDocument.getString("startDate");
                                            String endDateBooking = bookingDocument.getString("endDate");
                                            Date dateStartBooking = convertStringToDate(startDateBooking);
                                            Date dateEndBooking = convertStringToDate(endDateBooking);
                                            Date dateStartSearch = convertStringToDate(tvCheckIn.getText().toString());
                                            Date dateEndSearch = convertStringToDate(tvCheckOut.getText().toString());
                                            Log.d("dateStartBooking",startDateBooking+"");
                                            Log.d("dateEndBooking",endDateBooking+"");
                                            Log.d("dateStartSearch",tvCheckOut.getText().toString()+"");
                                            Log.d("dateEndSearch",tvCheckIn.getText().toString()+"");

                                            if (dateStartBooking != null && dateEndBooking != null) {
                                                if ((dateStartBooking.before(dateStartSearch) && dateEndBooking.before(dateStartSearch))
                                                        || (dateStartBooking.after(dateEndSearch) && dateEndBooking.after(dateEndSearch))) {
                                                    count2++;

                                                }
                                            }
                                        }


                                        if (count == count2) {
                                            String roomName = roomDocument.getString("roomName");
                                            List<String> roomImage = (List<String>) roomDocument.get("roomImage");
                                            if (roomImage != null) {
                                                String[] roomImageArray = new String[roomImage.size()];
                                                for (int i = 0; i < roomImage.size(); i++) {
                                                    roomImageArray[i] = roomImage.get(i);
                                                }
                                                ArrayList<SlideModel> dynamicImageList = new ArrayList<>();
                                                for (String imageUrl : roomImageArray) {
                                                    dynamicImageList.add(new SlideModel(imageUrl, "", ScaleTypes.CENTER_CROP));
                                                }
                                                imageList.addAll(dynamicImageList);
                                                List<Boolean> roomService = (List<Boolean>) roomDocument.get("roomAmenities");
                                                if (roomService != null) {
                                                    boolean[] roomServiceArray = new boolean[roomService.size()];
                                                    for (int i = 0; i < roomService.size(); i++) {
                                                        roomServiceArray[i] = roomService.get(i);
                                                    }
                                                    long roomPrice = roomDocument.getLong("roomPrice");
                                                    int roomPriceInt = (int) roomPrice;
                                                    boolean hasService1 = roomServiceArray[0];
                                                    boolean hasService2 = roomServiceArray[1];
                                                    boolean hasService3 = roomServiceArray[2];
                                                    boolean hasService4 = roomServiceArray[3];
                                                    boolean hasService5 = roomServiceArray[4];
                                                    boolean hasService6 = roomServiceArray[5];
                                                    Log.d("add","add");
                                                    listRoom.add(new ListRoomModel(hotelID,imageList, roomPriceInt, roomName, hasService1, hasService2, hasService3, hasService4, hasService5,hasService6));

                                                }
                                            }
                                            listRoomAdapter.setData(listRoom);
                                            rcvListRoom.setAdapter(listRoomAdapter);
                                        }

                                    }
                                });
                    }
                }
            }
        });

    }

    private Date convertStringToDate(String dateString) {
        try {
            if (dateString != null && dateString.length() > 0) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                return format.parse(dateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initUI(){
        context = ListRoom.this;
        rcvListRoom = findViewById(R.id.rcvListRoom);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        linearLayout = findViewById(R.id.selectTimeLayout);
        tvCheckIn = findViewById(R.id.tvcheckIn);
        tvCheckOut = findViewById(R.id.tvcheckOut);




        // Add TextWatcher to tvCheckIn
        tvCheckIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateAdapter();

                getListRoom();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not needed
            }
        });

        // Add TextWatcher to tvCheckOut
        tvCheckOut.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateAdapter();
                getListRoom();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not needed
            }
        });
    }

    private void updateAdapter() {
        // Update the adapter with the new checkIn and checkOut values
        listRoomAdapter.updateCheckInOut(tvCheckIn.getText().toString(), tvCheckOut.getText().toString());
        // Notify the adapter that the data has changed
        listRoomAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == RESULT_OK && data != null) {
            checkIn = data.getStringExtra("checkIn");
            checkOut = data.getStringExtra("checkOut");
            tvCheckIn.setText(checkIn);
            tvCheckOut.setText(checkOut);

            listRoomAdapter.updateCheckInOut(checkIn, checkOut);
        }
    }
}