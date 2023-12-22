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
import java.util.Date;
import java.util.List;

public class Content_Discount_Available extends Fragment {
    private RecyclerView rcvDiscount;
    private DiscountAvailableAdapter discountAvailableAdapter;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content__discount__available, container, false);
        rcvDiscount = view.findViewById(R.id.rcv_discount_available);
        discountAvailableAdapter = new DiscountAvailableAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false);
        rcvDiscount.setLayoutManager(linearLayoutManager);

        fetchDiscount();


        return view;
    }


    private List<DiscountAvailable> fetchDiscount(){
        List<DiscountAvailable> listDiscountAvailable = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference promotionCollection = db.collection("promotionApp");

        promotionCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Dữ liệu đã được truy vấn thành công
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d("sadasd","test");
                    // Lấy giá trị của các trường trong document
                    String promotionName = document.getString("promotionName");
                    String promotionPercent = document.getString("promotionPercent");
                    String promotionDetail = document.getString("promotionDetail");
                    Boolean isDay = document.getBoolean("isDay");
                    Boolean isNight = document.getBoolean("isNight");
                    String promotionDateStart = document.getString("promotionDateStart");
                    String promotionDateEnd = document.getString("promotionDateEnd");

                    Date dateStart = convertStringToDate(promotionDateStart);
                    Date dateEnd = convertStringToDate(promotionDateEnd);
                    listDiscountAvailable.add(new DiscountAvailable(promotionName, promotionPercent, promotionDetail, isDay,isNight,dateStart, dateEnd));

                }
            } else {
                // Đã xảy ra lỗi khi truy vấn dữ liệu
                Log.d("FirestoreQuery", "Error getting documents: ", task.getException());
            }
            discountAvailableAdapter.setData(listDiscountAvailable);
            rcvDiscount.setAdapter(discountAvailableAdapter);
        });
        return listDiscountAvailable;

    }

    private Date convertStringToDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày tháng
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}