package com.example.finalproject.Fragment.Content_Account_Fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy.Terms_PrivacyPolicy;
import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class AccountFragment extends Fragment {

    private TextView tvTermsNprivacy,changeLanguage,tvName, tvEmail,tvPhoneNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        changeLanguage =  view.findViewById(R.id.changeLanguage);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvPhoneNumber = view.findViewById(R.id.tvPhoneNumber);

        Locale currentLocale = getResources().getConfiguration().locale;
        String currentLanguage = currentLocale.getLanguage();

        tvTermsNprivacy = view.findViewById(R.id.termsNprivacy);
        if ("vi".equals(currentLanguage)) {
            changeLanguage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.outline_language_24, 0, R.drawable.vietnam, 0);
        } else if ("en".equals(currentLanguage)) {
            changeLanguage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.outline_language_24, 0, R.drawable.united_states_of_america, 0);
        }

        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy Drawable bên phải của TextView
                Drawable rightDrawable = changeLanguage.getCompoundDrawables()[2]; // 2 là chỉ số của bên phải

                // Kiểm tra xem Drawable bên phải có tên là "@drawable/vietnam" hay không
                if (rightDrawable != null && rightDrawable.getConstantState() != null) {
                    if (rightDrawable.getConstantState().equals(getResources().getDrawable(R.drawable.vietnam).getConstantState())) {
                        // Thực hiện khởi động Activity với kết quả là "vn"
                        Intent intent = new Intent(getActivity(), ChangeLanguage.class);
                        intent.putExtra("result", "vn");
                        startActivityForResult(intent, 111);
                    } else if (rightDrawable.getConstantState().equals(getResources().getDrawable(R.drawable.united_states_of_america).getConstantState())) {
                        // Thực hiện khởi động Activity với kết quả là "en"
                        Intent intent = new Intent(getActivity(), ChangeLanguage.class);
                        intent.putExtra("result", "en");
                        startActivityForResult(intent, 111);
                    }
                }
            }


        });

        fetchUserData();
        tvTermsNprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Terms_PrivacyPolicy.class);
                startActivity(intent);
            }
        });
        return view;

    }



    private void fetchUserData(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();

            // Reference to Firestore instance
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Reference to the "users" collection
            CollectionReference usersCollection = db.collection("users");

            // Query the user by userID
            usersCollection.document(userId).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            // User data found in Firestore
                            String name = documentSnapshot.getString("name");
                            String phoneNumber = documentSnapshot.getString("phoneNumber");
                            String email = documentSnapshot.getString("email");

                            tvName.setText(name);
                            tvEmail.setText(email);
                            tvPhoneNumber.setText(phoneNumber);
                            // Now you can use name, phoneNumber, email as needed
                            Log.d("TAG", "User data: Name - " + name + ", Phone - " + phoneNumber + ", Email - " + email);
                        } else {
                            // User data not found in Firestore
                            Log.d("TAG", "No such document");
                        }
                    })
                    .addOnFailureListener(e -> {
                        // Error fetching user data from Firestore
                        Log.w("TAG", "Error getting document", e);
                    });
        }

    }
}