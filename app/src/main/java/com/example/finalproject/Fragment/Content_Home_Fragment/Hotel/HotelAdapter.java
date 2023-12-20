package com.example.finalproject.Fragment.Content_Home_Fragment.Hotel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<Hotel> mHotel;
    private Context context;
    public void setData(List<Hotel> list){
        if (mHotel == null) {
            mHotel = new ArrayList<>();
        }
        mHotel.addAll(list);
        notifyDataSetChanged();
    }

    // Interface để xử lý sự kiện click
    public interface OnHotelItemClickListener {
        void onHotelItemClick(int position);
    }

    private OnHotelItemClickListener onHotelItemClickListener;

    // Setter để thiết lập listener
    public void setOnHotelItemClickListener(OnHotelItemClickListener listener) {
        this.onHotelItemClickListener = listener;
    }
    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_view,parent,false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, final int position) {
        Hotel hotel = mHotel.get(position);
        if (hotel == null) {
            return;
        }

        // Use data directly from the Hotel object
        holder.tvName.setText(hotel.getTitle());
        holder.tvAddressHotel.setText(hotel.getAddressHotel());
        holder.tvCost.setText(String.valueOf(hotel.getCost()));
        holder.tvRate.setText(hotel.getRate()+"");

        // Load symbolic image using Glide
        Glide.with(holder.imgHotel.getContext())
                .load(hotel.getImgHotel())
                .into(holder.imgHotel);

        // Set click listener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION && onHotelItemClickListener != null) {
                    onHotelItemClickListener.onHotelItemClick(adapterPosition);
                }
            }
        });
    }




    @Override
    public int getItemCount() {
        if(mHotel != null){
            return mHotel.size();
        }
        return 0;
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgHotel;
        private TextView tvName,tvAddressHotel,tvCost,tvRate,tvDiscount;
        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHotel = itemView.findViewById(R.id.imgHotel);
            tvName = itemView.findViewById(R.id.tvTitle);
            tvAddressHotel = itemView.findViewById(R.id.tvAddressHotel);
            tvCost = itemView.findViewById(R.id.tvCost);
            tvRate = itemView.findViewById(R.id.tvRate);
            tvDiscount = itemView.findViewById(R.id.tvDiscount);
        }
    }


}
