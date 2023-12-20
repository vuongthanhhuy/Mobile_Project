package com.example.finalproject.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.User.User;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;


public class RegistrationActivity extends AppCompatActivity {
    private EditText edtEmail,edtPass,edtName,edtPNumber;
    private Button btnSignUp;

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        edtEmail = findViewById(R.id.edt_email_signUp);
        edtPass = findViewById(R.id.edt_password_signUp);
        edtName = findViewById(R.id.edtName);
        edtPNumber = findViewById(R.id.edtPhoneNumber);
        btnSignUp = findViewById(R.id.btn_signUp);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }
    private void signUp(){
        String userEmail = edtEmail.getText().toString();
        String userPassword = edtPass.getText().toString();
        String userName = edtName.getText().toString();
        String userPhoneNumber = edtPNumber.getText().toString();

        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Enter your Email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userPassword.length() < 6){
            Toast.makeText(this, "Password to short", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Enter your name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPhoneNumber)){
            Toast.makeText(this, "Enter your phone number!", Toast.LENGTH_SHORT).show();
            return;
        }
        addUserToFirestore(userEmail, userPassword,userName, userPhoneNumber);


    }

    private void addUserToFirestore(String userEmail, String userPassword, String userName, String userPNumber) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        // Create a new user in Firebase Authentication
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        if (firebaseUser != null) {
                            // Firebase Authentication successful, now add user to Firestore
                            String userId = firebaseUser.getUid();

                            // Create a Map for the user data
                            Map<String, Object> user = new HashMap<>();
                            user.put("email", userEmail);
                            user.put("name", userName);
                            user.put("phoneNumber", userPNumber);

                            // Add the user data to the "users" collection with the UID as the document ID
                            db.collection("users")
                                    .document(userId)
                                    .set(user)
                                    .addOnSuccessListener(documentReference -> {
                                        Toast.makeText(RegistrationActivity.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegistrationActivity.this, Login.class));
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("TAG", "createUserWithEmail:failure", e);
                    Toast.makeText(this, "Authentication failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }






}