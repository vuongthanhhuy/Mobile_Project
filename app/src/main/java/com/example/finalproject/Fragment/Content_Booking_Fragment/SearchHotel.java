package com.example.finalproject.Fragment.Content_Booking_Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.HotelModel;
import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.SearchHotelAdapter;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SearchHotel extends AppCompatActivity {

    private Toolbar toolbar;
    private SearchHotelAdapter listSearchHotelAdapter;
    private RecyclerView rcvSearchResult;
    private TextView subtitle1TextView, subtitle2TextView;
    private boolean isAvailable,check;
    private String checkIn, checkOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        initUI();

        getAvaibleHotel(new OnListAvailableCallback() {
            @Override
            public void onListAvailable(List<String> listHotelAvailable) {
                // Dữ liệu đã sẵn sàng, bạn có thể sử dụng listHotelAvailable ở đây
                for (String availableHotelID : listHotelAvailable) {
                    Log.d("AvailableHotelID", availableHotelID);
                }

                // Gọi hàm getListHotel() và sử dụng listHotelAvailable
                getListHotel(listHotelAvailable);

                // Thiết lập checkIn và checkOut cho adapter
                listSearchHotelAdapter.setCheckInOut(checkIn, checkOut);
            }
        });


        listSearchHotelAdapter = new SearchHotelAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvSearchResult.setLayoutManager(linearLayoutManager);


        Intent intent = getIntent();
        if (intent != null) {
            String type = intent.getStringExtra("type");
            checkIn = intent.getStringExtra("checkIn");
            checkOut = intent.getStringExtra("checkOut");
            subtitle1TextView = new TextView(this);
            subtitle1TextView.setText("   "+checkIn);
            subtitle1TextView.setTextColor(Color.BLACK);
            subtitle1TextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            subtitle1TextView.setTypeface(null, Typeface.BOLD);

            subtitle2TextView = new TextView(this);
            subtitle2TextView.setTextColor(Color.BLACK);
            subtitle2TextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            subtitle2TextView.setText("   "+checkOut);
            subtitle2TextView.setTypeface(null, Typeface.BOLD);

            LinearLayout subtitleLayout = new LinearLayout(this);
            subtitleLayout.setOrientation(LinearLayout.VERTICAL);
            subtitleLayout.addView(subtitle1TextView);
            subtitleLayout.addView(subtitle2TextView);
            getSupportActionBar().setCustomView(subtitleLayout);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
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

    private List<HotelModel> getListHotel(List<String> listHotelAvailable) {
        List<HotelModel> listHotelModel = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference hotelCollection = db.collection("hotel");

        hotelCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    String hotelID = document.getString("hotelID");

                    if (listHotelAvailable.contains(hotelID)) {
                        String hotelName = document.getString("hotelName");
                        String hotelAddress = document.getString("hotelAddress");
                        Long hotelPrice = document.getLong("hotelPrice");
                        String hotelSymbolicImage = document.getString("hotelSymbolicImage");
                        String districtAndWard = extractDistrictAndWard(hotelAddress);

                        int price = (hotelPrice != null) ? hotelPrice.intValue() : 0;

                        // Add the hotel to the list
                        HotelModel hotelModel = new HotelModel(hotelID, hotelName, districtAndWard, hotelSymbolicImage, price, 100);
                        if(!listHotelModel.contains(hotelModel)){
                            listHotelModel.add(hotelModel);
                        }
                    }
                }

                // Dùng danh sách hotelList ở đây
                listSearchHotelAdapter.setData(listHotelModel);
                rcvSearchResult.setAdapter(listSearchHotelAdapter);
            }
        });

        return listHotelModel;
    }
    public interface OnListAvailableCallback {
        void onListAvailable(List<String> listHotelAvailable);
    }

    private void getAvaibleHotel(OnListAvailableCallback callback) {
        List<HotelModel> listHotelModel = new ArrayList<>();
        List<String> listHotelAvailable = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference hotelCollection = db.collection("hotel");

        hotelCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    String hotelID = document.getString("hotelID");

                    CollectionReference roomCollection = hotelCollection.document(hotelID).collection("room");
                    roomCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot roomDocument : task.getResult()) {
                                    String roomID = roomDocument.getString("roomID");
                                    CollectionReference bookingCollection = db.collection("booking");
                                    bookingCollection.whereEqualTo("roomID", roomID)
                                            .get()
                                            .addOnCompleteListener(task2 -> {
                                                if (task2.isSuccessful()) {
                                                    int count = task2.getResult().size();
                                                    int count2 = 0;
//                                                    Log.d("roomID",roomID);
                                                    for (QueryDocumentSnapshot bookingDocument : task2.getResult()) {
                                                        // Logic kiểm tra sự sẵn có của khách sạn
                                                        String startDateBooking = bookingDocument.getString("startDate");
                                                        String endDateBooking = bookingDocument.getString("endDate");

                                                        Date dateStartBooking = convertStringToDate(startDateBooking);
                                                        Date dateEndBooking = convertStringToDate(endDateBooking);
                                                        Date dateStartSearch = convertStringToDate(checkIn);
                                                        Date dateEndSearch = convertStringToDate(checkOut);
                                                        Log.d("startDate",startDateBooking);
                                                        Log.d("endDate",endDateBooking);
                                                        Log.d("checkIn",checkIn);
                                                        Log.d("checkOut",checkOut);
                                                        Log.d("roomID",roomID);
                                                        if (dateStartBooking != null && dateEndBooking != null) {
                                                            if ((dateStartBooking.before(dateStartSearch) && dateEndBooking.before(dateStartSearch))
                                                                    || (dateStartBooking.after(dateEndSearch) && dateEndBooking.after(dateEndSearch))) {
                                                                isAvailable = true;
                                                                Log.d("add","add");
                                                                count2++;
                                                            } else {
                                                                isAvailable = false;
                                                            }
                                                        }
                                                    }
                                                    if (count == count2) {
                                                        if (!listHotelAvailable.contains(hotelID)) {
                                                            listHotelAvailable.add(hotelID);
                                                        }
                                                    }
                                                }
                                            });
                                }
                            }
                        }
                    });
                }
                hotelCollection.get().addOnCompleteListener(task3 -> {
                    if (task.isSuccessful()) {
                        callback.onListAvailable(listHotelAvailable);
                    }
                });
            }
        });

        // Khi tất cả các công việc không đồng bộ hoàn thành, gọi callback

    }


    private String extractDistrictAndWard(String hotelAddress) {
        int quanIndex = hotelAddress.indexOf("Quận");
        int commaIndex = hotelAddress.indexOf(",", quanIndex);

        if (quanIndex != -1 && commaIndex != -1) {
            return hotelAddress.substring(quanIndex, commaIndex).trim();
        } else {
            return ""; // hoặc xử lý tương ứng nếu không tìm thấy
        }
    }
    private Date convertStringToDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}