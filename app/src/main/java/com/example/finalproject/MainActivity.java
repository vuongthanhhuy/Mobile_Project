package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.finalproject.Fragment.AccountFragment;
import com.example.finalproject.Fragment.BookingFragment;
import com.example.finalproject.Fragment.Content_Discount_Fragment.DiscountFragment;
import com.example.finalproject.Fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView BNView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new HomeFragment());

        BNView = findViewById(R.id.bottomNavigationView);
        BNView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id ==R.id.home){
                replaceFragment(new HomeFragment());
            }else if(id == R.id.book) {
                replaceFragment(new BookingFragment());
            }else if(id == R.id.discount){
                replaceFragment(new DiscountFragment());
            }else if(id == R.id.account){
                replaceFragment(new AccountFragment());
            }
            return true;
        });
        //Set ngôn ngữ mặc định của app
        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String selectedLanguage = preferences.getString("My_Lang", ""); // Lấy ngôn ngữ đã lưu
        if (!selectedLanguage.isEmpty()) {
            // Sử dụng ngôn ngữ đã chọn bởi người dùng
            Locale locale = new Locale(selectedLanguage);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        } else {
            // Sử dụng ngôn ngữ mặc định (Tiếng Việt)
            Locale locale = new Locale("vi");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        }




    }


    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }


}