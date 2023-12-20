package com.example.finalproject.Fragment.Content_Booking_Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy.CustomPagerAdapter;
import com.example.finalproject.Fragment.Content_Discount_Fragment.Content_Discount_Available;
import com.example.finalproject.Fragment.Content_Discount_Fragment.Content_Discount_History;
import com.example.finalproject.Fragment.Content_Discount_Fragment.DiscountFragmentPagerAdapter;
import com.example.finalproject.R;
import com.google.android.material.tabs.TabLayout;

public class Dialog_Select_Night_Day extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CustomPagerAdapter customPagerAdapter;
    private Button btnApply;
    private TextView tvTimeCheckIn, tvCheckIn, tvCheckOut, tvCheckInDay, tvCheckOutDay;
    private EditText edtNumDay;
    private int currentTabPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_select_night_day);

        tabLayout = findViewById(R.id.tabLayoutDialog);
        viewPager = findViewById(R.id.viewpagerDialog);
        btnApply = findViewById(R.id.btnApply);
        tabLayout.setupWithViewPager(viewPager);



        String title1 = getResources().getString(R.string.byNight);
        String title2 = getResources().getString(R.string.byDay);
        customPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        customPagerAdapter.addFragment(new Dialog_Content_Night(), title1);
        customPagerAdapter.addFragment(new Dialog_Content_Day(), title2);
        viewPager.setAdapter(customPagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTabPosition = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Không cần quan tâm khi tab không được chọn.
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Không cần quan tâm khi tab được chọn lại.
            }
        });
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTimeCheckIn = findViewById(R.id.tvTimeCheckInNight);
                tvCheckIn = findViewById(R.id.tvCheckInNight);
                tvCheckOut = findViewById(R.id.tvCheckOutNight);
                tvCheckInDay = findViewById(R.id.tvCheckInDay);
                tvCheckOutDay = findViewById(R.id.tvCheckOutDay);
                edtNumDay = findViewById(R.id.numDay);



                Intent resultIntent = new Intent();

                if(currentTabPosition == 0){
                    String checkInNight = tvTimeCheckIn.getText().toString() + " " + tvCheckIn.getText().toString();
                    String checkOutNight = tvCheckOut.getText().toString();
                    resultIntent.putExtra("checkIn", checkInNight);
                    resultIntent.putExtra("checkOut", checkOutNight);
                    setResult(RESULT_OK, resultIntent);
                }else if (currentTabPosition == 1){
                    if(edtNumDay.getText().toString().equals("")){
                        Toast.makeText(Dialog_Select_Night_Day.this, "", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String checkInDay = tvCheckInDay.getText().toString();
                    String checkOutDay = tvCheckOutDay.getText().toString();
                    resultIntent.putExtra("checkIn", checkInDay);
                    resultIntent.putExtra("checkOut", checkOutDay);
                    setResult(RESULT_OK, resultIntent);
                }

                finish();
            }
        });

    }
}