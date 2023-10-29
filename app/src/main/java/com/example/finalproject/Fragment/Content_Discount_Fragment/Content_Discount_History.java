package com.example.finalproject.Fragment.Content_Discount_Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;

import java.util.ArrayList;
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

        discountHistoryAdapter.setData(getListDiscount());
        rcvDiscount.setAdapter(discountHistoryAdapter);

        return view;
    }
    private List<DiscountHistory> getListDiscount(){
        List<DiscountHistory> listDiscountHistory = new ArrayList<>();

        listDiscountHistory.add(new DiscountHistory("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", false, true, true, new Date(123, 10, 31), new Date(123, 12, 31), new Date(123,9,20)));
        listDiscountHistory.add(new DiscountHistory("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31), new Date(123,9,20)));
        listDiscountHistory.add(new DiscountHistory("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31), new Date(123,9,20)));
        listDiscountHistory.add(new DiscountHistory("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31), new Date(123,9,20)));
        listDiscountHistory.add(new DiscountHistory("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31), new Date(123,9,20)));
        listDiscountHistory.add(new DiscountHistory("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31), new Date(123,9,20)));

        return listDiscountHistory;
    }
}