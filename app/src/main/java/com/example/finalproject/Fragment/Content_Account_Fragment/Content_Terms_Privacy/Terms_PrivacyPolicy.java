package com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

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

        Terms_PrivacyAdapter termsPrivacyAdapter = new Terms_PrivacyAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        termsPrivacyAdapter.addFragmentTermsPrivacyPolicy(new Content_Service_Agreement(), title1);
        termsPrivacyAdapter.addFragmentTermsPrivacyPolicy(new Content_Privacy_Policy(), title2);
        termsPrivacyAdapter.addFragmentTermsPrivacyPolicy(new Content_Payment_Policy(), title3);

        viewPager.setAdapter(termsPrivacyAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//        // Gọi onBackPressed để quay lại trang trước (AccountFragment) với kết quả RESULT_OK
//        setResult(Activity.RESULT_OK);
//        super.onBackPressed();
//    }
}