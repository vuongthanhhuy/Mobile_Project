package com.example.finalproject.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finalproject.R;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getDataIntent();
        setTitleToolbar();
    }

    private void getDataIntent(){
        String strPhoneNumber = getIntent().getStringExtra("phone_number");
        TextView tvUserInfor = findViewById(R.id.tv_user_infor);
        tvUserInfor.setText(strPhoneNumber);
    }
    private void setTitleToolbar(){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Login Activity");
        }
    }
}