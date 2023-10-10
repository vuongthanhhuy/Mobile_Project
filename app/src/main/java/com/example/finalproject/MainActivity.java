package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
//        Locale locale = new Locale("vi"); // Đặt ngôn ngữ mặc định là Tiếng Việt
//        Locale.setDefault(locale);


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

    }
    public void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }


}