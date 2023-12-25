package com.example.finalproject.Fragment.Content_Home_Fragment.CategoryHotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.HotelAdapter;
import com.example.finalproject.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private Context mContext;
    private List<CategoryModel> mListCategoryModel;

    public CategoryAdapter(Context mContext){
        this.mContext = mContext;

    }

    public void setData(List<CategoryModel> list){
        this.mListCategoryModel = list;
        notifyDataSetChanged();
    }

    // Interface để xử lý sự kiện click từ HotelAdapter
    public interface OnCategoryItemClickListener {
        void onCategoryItemClick(int position);
    }

    private OnCategoryItemClickListener onCategoryItemClickListener;

    // Setter để thiết lập listener từ HomeFragment
    public void setOnCategoryItemClickListener(OnCategoryItemClickListener listener) {
        this.onCategoryItemClickListener = listener;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryModel categoryModel = mListCategoryModel.get(position);
        if (categoryModel == null) {
            return;
        }
        holder.tvNameCategory.setText(categoryModel.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvHotel.setLayoutManager(linearLayoutManager);

        // Khởi tạo hotelAdapter
        HotelAdapter hotelAdapter = new HotelAdapter();
        hotelAdapter.setData(categoryModel.getHotels());
        holder.rcvHotel.setAdapter(hotelAdapter);

        // Thiết lập sự kiện click từ HotelAdapter
        hotelAdapter.setOnHotelItemClickListener(new HotelAdapter.OnHotelItemClickListener() {
            @Override
            public void onHotelItemClick(int position, String hotelID) {
                // Handle item click
                //Log.d("HotelAdapter", "Item clicked at position: " + position + ", Hotel Title: " + hotelTitle);
                // Now you have the hotel title and can use it as needed
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mListCategoryModel != null){
            return mListCategoryModel.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameCategory;
        private RecyclerView rcvHotel;

        // Thêm trường hotelAdapter
        private HotelAdapter hotelAdapter;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            rcvHotel = itemView.findViewById(R.id.rcv_hotel);

            // Khởi tạo hotelAdapter
            hotelAdapter = new HotelAdapter();
        }
    }
}
