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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference hotelCollection = db.collection("hotel");

        hotelCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Category> categoryList = new ArrayList<>();

                for (DocumentSnapshot document : task.getResult()) {
                    String hotelCategory = document.getString("category");

                    if (hotelCategory != null) {
                        // Lấy tất cả trường từ Firestore
                        String hotelID = document.getId();
                        String hotelName = document.getString("hotelName");
                        String hotelAddress = document.getString("hotelAddress");
                        Long hotelPrice = document.getLong("hotelPrice");
                        String hotelSymbolicImage = document.getString("hotelSymbolicImage");
                        // Xử lý địa chỉ để lấy quận và phường
                        String districtAndWard = extractDistrictAndWard(hotelAddress);

                        // Chuyển đổi giá từ Long sang int
                        int price = (hotelPrice != null) ? hotelPrice.intValue() : 0;

                        // Tạo đối tượng Hotel
                        Hotel hotel = new Hotel(hotelID, hotelName, districtAndWard, hotelSymbolicImage, price, 100);

                        // Tạo đối tượng Category nếu chưa tồn tại
                        Category category = getCategoryByName(categoryList, hotelCategory);

                        // Thêm hotel vào danh sách hotel của category
                        category.getHotels().add(hotel);
                    }
                }

                // Update adapter with the list of categories
                categoryAdapter.setData(categoryList);

            } else {
                // Handle errors
                // Display an error message if needed
            }
        });
    }

    private String extractDistrictAndWard(String hotelAddress) {
        int quanIndex = hotelAddress.indexOf("Quận");
        int commaIndex = hotelAddress.indexOf(",", quanIndex);

        if (quanIndex != -1 && commaIndex != -1) {
            return hotelAddress.substring(quanIndex, commaIndex).trim();
        } else {
            return ""; // hoặc xử lý tương ứng nếu không tìm thấy
        }
    }

    private Category getCategoryByName(List<Category> categoryList, String categoryName) {
        for (Category category : categoryList) {
            if (category.getNameCategory().equals(categoryName)) {
                return category;
            }
        }

        // Nếu category chưa tồn tại, tạo mới và thêm vào danh sách
        Category newCategory = new Category(categoryName, new ArrayList<>());
        categoryList.add(newCategory);
        return newCategory;
    }

}