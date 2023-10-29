package com.example.finalproject.Fragment.Content_Discount_Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Hotel.Hotel;
import com.example.finalproject.Hotel.HotelAdapter;
import com.example.finalproject.R;

import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder>{
    private List<Discount> mDiscount;
    public void setData(List<Discount> list){
        this.mDiscount = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_discount_item,parent,false);
        return new DiscountAdapter.DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        Discount discount = mDiscount.get(position);
        if(discount == null){
            return;
        }

        holder.title.setText(discount.getTitle());
        holder.percent.setText(discount.getPercent());
        if(discount.isTypeHours()){
        }
    }

    @Override
    public int getItemCount() {
        if(mDiscount != null){
            return mDiscount.size();
        }
        return 0;
    }

    public class DiscountViewHolder extends RecyclerView.ViewHolder{

        private TextView title, percent, typeHours, typeDay, typeNight, startDays, endDays;

        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.disTitle);
            percent = itemView.findViewById(R.id.disPercent);
            typeHours = itemView.findViewById(R.id.typeHours);
            typeDay = itemView.findViewById(R.id.typeDay);
            typeNight = itemView.findViewById(R.id.typeNight);
            startDays = itemView.findViewById(R.id.startDays);
            endDays = itemView.findViewById(R.id.endDays);
        }
    }
}
