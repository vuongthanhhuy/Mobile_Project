package com.example.finalproject.Fragment.Content_Booking_Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Fragment.Content_Discount_Fragment.DiscountAvailable;
import com.example.finalproject.Fragment.Content_Home_Fragment.CategoryHotel.CategoryAdapter;
import com.example.finalproject.R;

import java.util.List;


public class ServiceHotelAdapter extends RecyclerView.Adapter<ServiceHotelAdapter.ServiceViewHolder>{

    private List<ServiceHotel> mServiceHotel;
    private Context mContext;
    public ServiceHotelAdapter(Context mContext){
        this.mContext = mContext;

    }
    public void setData(List<ServiceHotel> list){
        this.mServiceHotel = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_service_hotel_details,parent,false);
        return new ServiceHotelAdapter.ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {

        ServiceHotel serviceHotel = mServiceHotel.get(position);
        if(serviceHotel == null){
            return;
        }
        if(!serviceHotel.isWifi()){
            holder.wifi.setVisibility(View.GONE);
        }
        if(!serviceHotel.isTV()){
            holder.TV.setVisibility(View.GONE);
        }
        if(!serviceHotel.isReceptionist()){
            holder.receptionist.setVisibility(View.GONE);
        }
        if(!serviceHotel.isBathroom()){
            holder.bathroom.setVisibility(View.GONE);
        }
        if(!serviceHotel.isBathtub()){
            holder.bathtub.setVisibility(View.GONE);
        }
        if(!serviceHotel.isKingbed()){
            holder.kingbed.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if(mServiceHotel != null){
            return mServiceHotel.size();
        }
        return 0;
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder{

        private TextView wifi, TV, receptionist, bathroom, bathtub, kingbed;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            wifi = itemView.findViewById(R.id.wifi);
            TV = itemView.findViewById(R.id.TV);
            receptionist = itemView.findViewById(R.id.receptionist);
            bathroom = itemView.findViewById(R.id.bathroom);
            bathtub = itemView.findViewById(R.id.bathtub);
            kingbed = itemView.findViewById(R.id.kingbed);
        }
    }
}
