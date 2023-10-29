package com.example.finalproject.Fragment.Content_Home_Fragment.Hotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<Hotel> mHotel;

    public void setData(List<Hotel> list){
        this.mHotel = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_view,parent,false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = mHotel.get(position);
        if(hotel == null){
            return;
        }

        holder.imgHotel.setImageResource(hotel.getResourceId());
        holder.tvTitle.setText(hotel.getTitle());
        holder.tvAddressHotel.setText(hotel.getAddressHotel());
        holder.tvTimeBooking.setText(hotel.getTimeBooking()+"");
        holder.tvCost.setText(hotel.getCost()+"");
        holder.tvRate.setText(hotel.getRate()+"");
        holder.tvDiscount.setText(hotel.getDiscount()+"");
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
        private TextView tvTitle,tvAddressHotel,tvTimeBooking,tvCost,tvRate,tvDiscount;
        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHotel = itemView.findViewById(R.id.imgHotel);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAddressHotel = itemView.findViewById(R.id.tvAddressHotel);
            tvTimeBooking = itemView.findViewById(R.id.tvTimeBooking);
            tvCost = itemView.findViewById(R.id.tvCost);
            tvRate = itemView.findViewById(R.id.tvRate);
            tvDiscount = itemView.findViewById(R.id.tvDiscount);
        }
    }
}
