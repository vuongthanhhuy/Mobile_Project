package com.example.finalproject.Fragment.Content_Account_Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.finalproject.MainActivity;
import com.example.finalproject.R;

import java.util.Locale;

public class ChangeLanguage extends AppCompatActivity {
    private RadioButton vnRadioButton;
    private RadioButton enRadioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        vnRadioButton = findViewById(R.id.vnRad);
        enRadioButton = findViewById(R.id.enRad);

        Button btnChangeLanguage= findViewById(R.id.button);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            String result = intent.getStringExtra("result");

            // Kiểm tra giá trị "result" và chọn radio button tương ứng
            if ("vn".equals(result)) {
                vnRadioButton.setChecked(true);
            } else if ("en".equals(result)) {
                enRadioButton.setChecked(true);
            }
        }
        btnChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId == vnRadioButton.getId()) {
                    setLocale("vi");
                } else if (selectedRadioButtonId == enRadioButton.getId()) {
                    setLocale("en");
                }
                recreate(); // Tải lại Activity để áp dụng ngôn ngữ mới
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                // Đảm bảo gọi navigateBackToAccountFragment() sau khi bạn đã áp dụng ngôn ngữ mới
                navigateBackToAccountFragment();

                finish();
            }
        });

    }
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Lưu ngôn ngữ đã chọn vào SharedPreferences hoặc SharedPreferences
        // Điều này giúp lưu trạng thái ngôn ngữ khi ứng dụng được khởi động lại.
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Đọc ngôn ngữ đã lưu từ SharedPreferences và cài đặt nó
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }

    private void navigateBackToAccountFragment() {
        // Sử dụng FragmentManager để quay lại fragment trước đó (AccountFragment)
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(); // Quay lại fragment trước đó
    }


}