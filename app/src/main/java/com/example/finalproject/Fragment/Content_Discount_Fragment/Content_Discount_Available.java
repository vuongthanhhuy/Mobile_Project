package com.example.finalproject.Fragment.Content_Discount_Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class Content_Discount_Available extends Fragment {
    private RecyclerView rcvDiscount;
    private DiscountAvailableAdapter discountAvailableAdapter;
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content__discount__available, container, false);
        rcvDiscount = view.findViewById(R.id.rcv_discount_available);
        discountAvailableAdapter = new DiscountAvailableAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false);
        rcvDiscount.setLayoutManager(linearLayoutManager);

        fetchDiscountAvailable();

        return view;
    }


    private List<DiscountAvailableModel> fetchDiscountAvailable(){
        List<DiscountAvailableModel> listDiscountAvailableModel = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference promotionCollection = db.collection("promotionApp");
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        Log.d("current",currentDate+"");
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
                    if (dateStart != null && dateEnd != null && dateStart.before(currentDate) && dateEnd.before(currentDate)) {
                    } else {
                        listDiscountAvailableModel.add(new DiscountAvailableModel(promotionID,promotionName, percent, condition, promotionDetail, isDay, isNight, dateStart, dateEnd));
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

}