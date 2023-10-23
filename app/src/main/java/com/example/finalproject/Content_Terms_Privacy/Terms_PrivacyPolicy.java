package com.example.finalproject.Content_Terms_Privacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.finalproject.Fragment.AccountFragment;
import com.example.finalproject.R;
import com.google.android.material.tabs.TabLayout;

public class Terms_PrivacyPolicy extends AppCompatActivity {
    private TabLayout tabLayout;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_privacy_policy);

        Intent intent = getIntent();

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);


        tabLayout.setupWithViewPager(viewPager);

        String title1 = getResources().getString(R.string.serviceAgreement);
        String title2 = getResources().getString(R.string.privacyPolicy);
        String title3 = getResources().getString(R.string.paymentPolicy);

        ContentAdapter contentAdapter = new ContentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        contentAdapter.addFragment(new Content_Service_Agreement(), title1);
        contentAdapter.addFragment(new Content_Privacy_Policy(), title2);
        contentAdapter.addFragment(new Content_Payment_Policy(), title3);

        viewPager.setAdapter(contentAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị nút quay lại
            // getSupportActionBar().setDisplayHomeAsUpEnabled(false); // Ẩn nút quay lại
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Gọi onBackPressed để quay lại trang trước (AccountFragment) với kết quả RESULT_OK
        setResult(Activity.RESULT_OK);
        super.onBackPressed();
    }
}