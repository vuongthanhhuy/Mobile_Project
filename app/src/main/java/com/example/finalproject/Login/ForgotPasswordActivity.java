package com.example.finalproject.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button btnResetPass,btnBack;
    EditText edtEmail;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnResetPass = findViewById(R.id.btn_reset_pass);
        btnBack = findViewById(R.id.btn_back);
        edtEmail = findViewById(R.id.edt_email_forgot);
        progressBar = findViewById(R.id.forgotProgressBar);

        mAuth = FirebaseAuth.getInstance();

        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = edtEmail.getText().toString().trim();
                if(!TextUtils.isEmpty(userEmail)){
                    resetPassword();
                }else{
                    edtEmail.setError("Email field can't be empty");
                }
            }


        });

    }

    private void resetPassword() {
        progressBar.setVisibility(View.VISIBLE);
        btnResetPass.setVisibility(View.INVISIBLE);

        mAuth.sendPasswordResetEmail(userEmail)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        startActivity(new Intent(ForgotPasswordActivity.this, Login.class));
                        Toast.makeText(ForgotPasswordActivity.this, "Reset Password link has been sent to your registered email", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgotPasswordActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        btnResetPass.setVisibility(View.VISIBLE);
                    }
                });
    }
}