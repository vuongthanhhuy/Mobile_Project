package com.example.finalproject.Fragment.Content_Booking_Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Content_Booking_Night extends Fragment {

    private LinearLayout layout;
    private Context context;
    private TextView tvCheckIn;
    private TextView tvCheckOut;
    private Button btnSearchNight;
    private String defaultTime;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content__booking__night, container, false);
        context = getContext();
        layout = view.findViewById(R.id.selectTimeLayout);
        tvCheckIn = view.findViewById(R.id.tvcheckIn);
        tvCheckOut = view.findViewById(R.id.tvcheckOut);
        btnSearchNight = view.findViewById(R.id.btnSearchNight);
        eventClick();
//        searchData();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date day = calendar.getTime();
        Date currentDate = new Date();
        String currentDateStr = dateFormat.format(currentDate);

        String defaultTime = "20:00:00";
        String checkIn = currentDateStr + " " + defaultTime;
        tvCheckIn.setText(checkIn);
        String nextDay = dateFormat.format(day)+ " 12:00:00";
        tvCheckOut.setText(nextDay);
        return view;
    }

    private void eventClick(){
        btnSearchNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchHotel.class);
                intent.putExtra("type", "Qua đêm");
                intent.putExtra("checkIn",tvCheckIn.getText().toString());
                intent.putExtra("checkOut",tvCheckOut.getText().toString());
                startActivity(intent);
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_select_night);

                Window window = dialog.getWindow();
                if(window == null){
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                WindowManager.LayoutParams windowAttributes = window.getAttributes();
                windowAttributes.gravity = Gravity.CENTER;
                window.setAttributes(windowAttributes);
                dialog.setCancelable(true);

                Button btnClose = dialog.findViewById(R.id.btnClose);
                Button btnApply = dialog.findViewById(R.id.btnApply);
                CalendarView selectedDateCalendar = dialog.findViewById(R.id.calendarView);
                TextView tvCheckInNight = dialog.findViewById(R.id.tvCheckInNight);
                TextView tvTimeCheckInNight = dialog.findViewById(R.id.tvTimeCheckInNight);
                TextView tvCheckOutNight = dialog.findViewById(R.id.tvCheckOutNight);
                ChipGroup chipGroupDay = dialog.findViewById(R.id.chipGroupDay);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                // Lấy ngày hiện tại
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                Date day = calendar.getTime();
                Date currentDate = new Date();
                String currentDateStr = dateFormat.format(currentDate);

                defaultTime = "20:00:00";
                tvCheckInNight.setText(currentDateStr);
                tvTimeCheckInNight.setText(defaultTime);
                String nextDay = dateFormat.format(day)+ " 12:00:00";
                tvCheckOutNight.setText(nextDay);

                chipGroupDay.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
                    @Override
                    public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i : checkedIds){
                            Chip chip = dialog.findViewById(i);
                            stringBuilder.append("").append(chip.getText());
                        }
                        String timeFormat = stringBuilder.toString().replaceFirst("","")+":00";
                        tvTimeCheckInNight.setText(timeFormat);

                    }
                });


                selectedDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String currentDay = sdf.format(selectedDate.getTime());
                        selectedDate.add(Calendar.DATE, 1);
                        String nextDay = sdf.format(selectedDate.getTime());
                        tvCheckInNight.setText(currentDay);
                        tvCheckOutNight.setText(nextDay + " 12:00:00");

                    }
                });
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnApply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvCheckIn.setText( tvCheckInNight.getText().toString()  +" "+ tvTimeCheckInNight.getText().toString());
                        tvCheckOut.setText(tvCheckOutNight.getText());

                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });




                dialog.show();
            }
        });
    }
    private void searchData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference hotelsCollection = db.collection("hotel");
        hotelsCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot hotelDoc : task.getResult()) {
                        // Get the hotelID
                        String hotelID = hotelDoc.getId();

                        CollectionReference roomsCollection = hotelDoc.getReference().collection("room");

                        roomsCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot roomDoc : task.getResult()) {
                                        // Get the roomID
                                        String roomID = roomDoc.getId();
                                        // Log or process the roomID and hotelID as needed
                                        Log.d("Room and Hotel IDs", "HotelID: " + hotelID + ", RoomID: " + roomID);

                                        // Now, you can check the "booking" collection for documents with matching hotelID and roomID
                                        checkBookingCollection(hotelID, roomID);
                                    }
                                } else {
                                    Log.d("Firestore", "Error getting documents: ", task.getException());
                                }
                            }
                        });
                    }
                } else {
                    Log.d("Firestore", "Error getting documents: ", task.getException());
                }
            }
        });



    }
    private void checkBookingCollection(String hotelID, String roomID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Reference to the "booking" collection
        CollectionReference bookingCollection = db.collection("booking");

        // Query to check for documents with matching hotelID and roomID
        Query query = bookingCollection.whereEqualTo("hotelID", hotelID).whereEqualTo("roomID", roomID);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot bookingDoc : task.getResult()) {
                        // Document in the "booking" collection with matching hotelID and roomID found
                        // You can log or process this information as needed
                        Log.d("Booking Info", "BookingID: " + bookingDoc.getId() +
                                ", HotelID: " + bookingDoc.getString("hotelID") +
                                ", RoomID: " + bookingDoc.getString("roomID"));
                    }
                } else {
                    Log.d("Firestore", "Error getting documents: ", task.getException());
                }
            }
        });
    }
}