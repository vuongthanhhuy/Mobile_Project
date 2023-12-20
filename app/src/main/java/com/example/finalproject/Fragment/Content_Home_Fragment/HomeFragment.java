package com.example.finalproject.Fragment.Content_Home_Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.finalproject.Fragment.Content_Booking_Fragment.HotelDetails;
import com.example.finalproject.Fragment.Content_Home_Fragment.CategoryHotel.Category;
import com.example.finalproject.Fragment.Content_Home_Fragment.CategoryHotel.CategoryAdapter;
import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.Hotel;
import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.HotelAdapter;
import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private HotelAdapter hotelAdapter;
    private List<Category> listCategory;
    private Context mContext;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcvCategory = view.findViewById(R.id.rcv_category);
        categoryAdapter = new CategoryAdapter(getContext());
        hotelAdapter = new HotelAdapter();
        mContext = getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        rcvCategory.setAdapter(categoryAdapter);

        // Fetch data from Firestore
        fetchDataFromFirestore();

        categoryAdapter.setOnCategoryItemClickListener(new CategoryAdapter.OnCategoryItemClickListener() {
            @Override
            public void onCategoryItemClick(int position) {
                // Handle category item click
                // You can add the logic to navigate to a new activity or fragment
                Toast.makeText(mContext, "asdasd", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    private void fetchDataFromFirestore() {
        // Access the Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Reference to the "hotel" collection
        CollectionReference hotelCollection = db.collection("hotel");

        // Fetch data from Firestore
        hotelCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Process the query result
                List<Hotel> hotelList1 = new ArrayList<>();
                List<Hotel> hotelList2 = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    String hotelCategory = document.getString("category");
                    if(hotelCategory.toString().equals("1")){
                        String hotelAddress = document.getString("hotelAddress");
                        String hotelName = document.getString("hotelName");
                        Long hotelPrice = document.getLong("hotelPrice");
                        int price = 0; // Giá trị mặc định
                        if (hotelPrice != null) {
                            price = hotelPrice.intValue();
                        }
                        String hotelSymbolicImage = document.getString("hotelSymbolicImage");
                        // Create a Hotel object and add it to the list
                        Hotel hotel = new Hotel(hotelName, hotelAddress, hotelSymbolicImage, price, 100);
                        hotelList1.add(hotel);
                    }else if(hotelCategory.toString().equals("2")){
                        String hotelAddress = document.getString("hotelAddress");
                        String hotelName = document.getString("hotelName");
                        Long hotelPrice = document.getLong("hotelPrice");
                        int price = 0; // Giá trị mặc định
                        if (hotelPrice != null) {
                            price = hotelPrice.intValue();
                        }
                        String hotelSymbolicImage = document.getString("hotelSymbolicImage");
                        // Create a Hotel object and add it to the list
                        Hotel hotel = new Hotel(hotelName, hotelAddress, hotelSymbolicImage, price, 100);
                        hotelList2.add(hotel);
                    }

                }

                // Create a Category object and set the list of hotels
                Category category1 = new Category("Category 1", hotelList1);
                Category category2 = new Category("Category 2", hotelList2);

                // Add the category to the list
                listCategory = new ArrayList<>();
                listCategory.add(category1);
                listCategory.add(category2);

                // Update the adapter with the list of categories
                categoryAdapter.setData(listCategory);

            } else {
                // Handle errors
                // You may want to display an error message
            }
        });
    }
}