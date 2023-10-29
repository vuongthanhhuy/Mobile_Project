package com.example.finalproject.Fragment.Content_Discount_Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.text.SimpleDateFormat;
import java.util.List;

    public class DiscountAvailableAdapter extends RecyclerView.Adapter<DiscountAvailableAdapter.DiscountViewHolder>{
    private List<DiscountAvailable> mDiscountAvailable;
    private Context mContext;

    private int  selectedTabPosition = 1;

        public DiscountAvailableAdapter(Context mContext){
        this.mContext = mContext;

    }
    public void setData(List<DiscountAvailable> list){
        this.mDiscountAvailable = list;
        notifyDataSetChanged();
    }


        @NonNull
        @Override
        public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = null;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_discount_item_available, parent, false);
            return new DiscountAvailableAdapter.DiscountViewHolder(view);
        }


        @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        DiscountAvailable discountAvailable = mDiscountAvailable.get(position);
        if(discountAvailable == null){
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String typeHour = mContext.getResources().getString(R.string.typeHours);
        String typeDay = mContext.getResources().getString(R.string.typeDay);
        String typeNight = mContext.getResources().getString(R.string.typeNight);
        String startDay = dateFormat.format(discountAvailable.getStartDay());
        String endDay = dateFormat.format(discountAvailable.getEndDay());

        holder.title.setText(discountAvailable.getTitle());
        holder.percent.setText(discountAvailable.getPercent());
        if(!discountAvailable.isTypeHours()){
            holder.typeHours.setVisibility(View.GONE);
        }else{
            holder.typeHours.setText(typeHour);
        }
        if(!discountAvailable.isTypeNight()){
            holder.typeNight.setVisibility(View.GONE);
        }else{
            holder.typeNight.setText(typeNight);
        }
        if(!discountAvailable.isTypeDay()){
            holder.typeDay.setVisibility(View.GONE);
        }else{
            holder.typeDay.setText(typeDay);
        }
        holder.startDays.setText(startDay);
        holder.endDays.setText(endDay);
    }

    @Override
    public int getItemCount() {
        if(mDiscountAvailable != null){
            return mDiscountAvailable.size();
        }
        return 0;
    }

    public class DiscountViewHolder extends RecyclerView.ViewHolder{

        private TextView title, percent, typeHours, typeDay, typeNight, startDays, endDays;
        private Button btnUse;
        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.disTitle);
            percent = itemView.findViewById(R.id.disPercent);
            typeHours = itemView.findViewById(R.id.typeHours);
            typeDay = itemView.findViewById(R.id.typeDay);
            typeNight = itemView.findViewById(R.id.typeNight);
            startDays = itemView.findViewById(R.id.startDays);
            endDays = itemView.findViewById(R.id.endDays);
            btnUse = itemView.findViewById(R.id.btnUse);
        }
    }
}
