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

        discountAvailableAdapter.setData(getListDiscount());
        rcvDiscount.setAdapter(discountAvailableAdapter);

        return view;
    }
    private List<DiscountAvailable> getListDiscount(){
        List<DiscountAvailable> listDiscountAvailable = new ArrayList<>();

        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));
        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));
        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));
        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));
        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));
        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));

        return listDiscountAvailable;
    }
}