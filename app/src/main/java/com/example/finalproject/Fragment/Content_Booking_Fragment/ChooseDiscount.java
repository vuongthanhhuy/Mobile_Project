package com.example.finalproject.Fragment.Content_Booking_Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.finalproject.Fragment.Content_Discount_Fragment.DiscountAvailable;
import com.example.finalproject.Fragment.Content_Discount_Fragment.DiscountAvailableAdapter;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChooseDiscount extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rcvDiscount;
    private DiscountAvailableAdapter discountAvailableAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_discount);

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

        discountAvailableAdapter.setData(getListDiscount());
        rcvDiscount.setAdapter(discountAvailableAdapter);
    }
    private List<DiscountAvailable> getListDiscount(){
        List<DiscountAvailable> listDiscountAvailable = new ArrayList<>();

//        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));
//        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));
//        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));
//        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));
//        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));
//        listDiscountAvailable.add(new DiscountAvailable("GIẢM 22% - Tối ĐA 22K (HCM)", "22%", true, true, true, new Date(123, 10, 31), new Date(123, 12, 31)));

        return listDiscountAvailable;
    }
}