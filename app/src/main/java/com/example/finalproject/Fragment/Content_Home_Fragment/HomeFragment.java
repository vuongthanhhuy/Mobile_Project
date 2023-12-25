package com.example.finalproject.Fragment.Content_Home_Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproject.Fragment.Content_Home_Fragment.CategoryHotel.CategoryModel;
import com.example.finalproject.Fragment.Content_Home_Fragment.CategoryHotel.CategoryAdapter;
import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.HotelModel;
import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.HotelAdapter;
import com.example.finalproject.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private HotelAdapter hotelAdapter;
    private List<CategoryModel> listCategoryModel;
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

        fetchDataFromFirestore();

        categoryAdapter.setOnCategoryItemClickListener(new CategoryAdapter.OnCategoryItemClickListener() {
            @Override
            public void onCategoryItemClick(int position) {
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
                List<CategoryModel> categoryModelList = new ArrayList<>();

                for (DocumentSnapshot document : task.getResult()) {
                    String hotelCategory = document.getString("category");

                    if (hotelCategory != null) {
                        String hotelID = document.getId();
                        String hotelName = document.getString("hotelName");
                        String hotelAddress = document.getString("hotelAddress");
                        Long hotelPrice = document.getLong("hotelPrice");
                        String hotelSymbolicImage = document.getString("hotelSymbolicImage");
                        String districtAndWard = extractDistrictAndWard(hotelAddress);

                        int price = (hotelPrice != null) ? hotelPrice.intValue() : 0;

                        HotelModel hotelModel = new HotelModel(hotelID, hotelName, districtAndWard, hotelSymbolicImage, price, 100);
                        CategoryModel categoryModel = getCategoryByName(categoryModelList, hotelCategory);
                        categoryModel.getHotels().add(hotelModel);
                    }
                }

                categoryAdapter.setData(categoryModelList);

            } else {
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

    private CategoryModel getCategoryByName(List<CategoryModel> categoryModelList, String categoryName) {
        for (CategoryModel categoryModel : categoryModelList) {
            if (categoryModel.getNameCategory().equals(categoryName)) {
                return categoryModel;
            }
        }

        // Nếu category chưa tồn tại, tạo mới và thêm vào danh sách
        CategoryModel newCategoryModel = new CategoryModel(categoryName, new ArrayList<>());
        categoryModelList.add(newCategoryModel);
        return newCategoryModel;
    }

}