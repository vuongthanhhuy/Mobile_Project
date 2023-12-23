package com.example.finalproject.Fragment.Content_Booking_Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.Fragment.Content_Discount_Fragment.DiscountAvailableModel;
import com.example.finalproject.Fragment.Content_Discount_Fragment.DiscountAvailableAdapter;
import com.example.finalproject.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChooseDiscount extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rcvDiscount;
    private DiscountAvailableAdapter discountAvailableAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_discount);

        initUI();
        fetchDiscountAvailable();
    }


    private List<DiscountAvailableModel> fetchDiscountAvailable(){
        Intent intent = getIntent();
        int priceRoom = intent.getIntExtra("price",0);
        List<DiscountAvailableModel> listDiscountAvailableModel = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference promotionCollection = db.collection("promotionApp");

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        promotionCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String promotionID = document.getString("promotionID");
                    String promotionName = document.getString("promotionName");
                    long promotionPercent = document.getLong("promotionPercent");
                    int percent = (int) promotionPercent;
                    long promotionCondition = document.getLong("promotionCondition");
                    int condition = (int) promotionCondition;
                    String promotionDetail = document.getString("promotionDetail");
                    Boolean isDay = document.getBoolean("isDay");
                    Boolean isNight = document.getBoolean("isNight");
                    String promotionDateStart = document.getString("promotionDateStart");
                    String promotionDateEnd = document.getString("promotionDateEnd");

                    Date dateStart = convertStringToDate(promotionDateStart);
                    Date dateEnd = convertStringToDate(promotionDateEnd);
                    if (dateStart != null && dateEnd != null &&
                            dateStart.before(currentDate) && dateEnd.before(currentDate) || priceRoom < condition) {

                    } else {
                        listDiscountAvailableModel.add(new DiscountAvailableModel(promotionID,promotionName, percent, condition , promotionDetail, isDay, isNight, dateStart, dateEnd));
                    }
                }
            } else {
                Log.d("FirestoreQuery", "Error getting documents: ", task.getException());
            }
            discountAvailableAdapter.setData(listDiscountAvailableModel);
            rcvDiscount.setAdapter(discountAvailableAdapter);
        });
        return listDiscountAvailableModel;

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

    private void initUI(){
        toolbar = findViewById(R.id.toolbarChooseDiscount);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rcvDiscount =findViewById(R.id.rcv_discount_available);
        discountAvailableAdapter = new DiscountAvailableAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcvDiscount.setLayoutManager(linearLayoutManager);

        discountAvailableAdapter.setOnUseClickListener(new DiscountAvailableAdapter.OnUseClickListener() {
            @Override
            public void onUseClick(String promotionID, int discountPercent) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("promotionID", promotionID);
                resultIntent.putExtra("discountPercent", discountPercent);
                setResult(RESULT_OK, resultIntent);
                Toast.makeText(ChooseDiscount.this, "Áp dụng khuyến mãi thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}