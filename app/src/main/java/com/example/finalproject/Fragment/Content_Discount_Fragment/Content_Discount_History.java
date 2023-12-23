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


public class Content_Discount_History extends Fragment {

    private RecyclerView rcvDiscount;
    private DiscountHistoryAdapter discountHistoryAdapter;
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Here, you can obtain mContext
        mContext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content__discount__history, container, false);
        rcvDiscount = view.findViewById(R.id.rcv_discount_history);
        discountHistoryAdapter = new DiscountHistoryAdapter(mContext);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false);
        rcvDiscount.setLayoutManager(linearLayoutManager);

        fetchDiscountHistoryModel();

        return view;
    }
    private List<DiscountHistoryModel> fetchDiscountHistoryModel(){
        List<DiscountHistoryModel> listDiscountHistoryModel = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference promotionCollection = db.collection("promotionApp");
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        promotionCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String promotionName = document.getString("promotionName");
                    long promotionPercent = document.getLong("promotionPercent");
                    int percent = (int) promotionPercent;
                    String promotionDetail = document.getString("promotionDetail");
                    Boolean isDay = document.getBoolean("isDay");
                    Boolean isNight = document.getBoolean("isNight");
                    String promotionDateStart = document.getString("promotionDateStart");
                    String promotionDateEnd = document.getString("promotionDateEnd");

                    Date dateStart = convertStringToDate(promotionDateStart);
                    Date dateEnd = convertStringToDate(promotionDateEnd);

                    if (dateStart != null && dateEnd != null && dateEnd.after(currentDate)) {
                    } else {
                        listDiscountHistoryModel.add(new DiscountHistoryModel(promotionName, percent, promotionDetail, isDay, isNight, dateStart, dateEnd));
                    }
                }
            } else {
                Log.d("FirestoreQuery", "Error getting documents: ", task.getException());
            }
            discountHistoryAdapter.setData(listDiscountHistoryModel);
            rcvDiscount.setAdapter(discountHistoryAdapter);
        });
        return listDiscountHistoryModel;

    }

    private Date convertStringToDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}