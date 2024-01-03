package com.example.finalproject.Fragment.Content_Home_Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.finalproject.Fragment.Content_Home_Fragment.CategoryHotel.CategoryModel;
import com.example.finalproject.Fragment.Content_Home_Fragment.CategoryHotel.CategoryAdapter;
import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.HotelModel;
import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.HotelAdapter;
import com.example.finalproject.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private HotelAdapter hotelAdapter;
    private List<CategoryModel> listCategoryModel;
    private Context mContext;
    private ImageSlider imageSlider;
    private TextView tvCurrentTime;
    private Handler handler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvCurrentTime = view.findViewById(R.id.tvCurrentTime);
        handler = new Handler(Looper.getMainLooper());
        updateCurrentTime();
        imageSlider = view.findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel("https://www.cet.edu.vn/wp-content/uploads/2018/09/uu-dai.jpg", "Ưu đãi lên tới 70%", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://www.bestviewhotel.com.my/app/webroot/upload/images/Voucher%20Promo-02.jpg", "Đặt phòng ngay để giảm giá 45% 1 đêm ", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://www.amarinsamuiresort.com/images/promotion/banner-promotion-amarin-1.jpg", "Khuyến mãi đặt biệt ", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://www.oneworldhotel.com.my/wp-content/uploads/sites/237/2022/05/WEBSITE_Stay-Dine-Rebate-RM88-FA_Website_Website-500x250.jpg", "Đặt phòng ngay để nhận ưu đãi", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(imageList);
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

    private void updateCurrentTime() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Lấy thời gian hiện tại
                long currentTimeMillis = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                String currentTimeString = sdf.format(new Date(currentTimeMillis));

                // Cập nhật TextView
                tvCurrentTime.setText(currentTimeString);

                // Tiếp tục cập nhật sau mỗi giây
                updateCurrentTime();
            }
        }, 1000);
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Loại bỏ các callbacks để tránh rò rỉ bộ nhớ khi Activity bị hủy
        handler.removeCallbacksAndMessages(null);
    }
}