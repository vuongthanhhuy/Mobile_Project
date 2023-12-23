package com.example.finalproject.Fragment.Content_Discount_Fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Fragment.Content_Booking_Fragment.Confirm_Payment;
import com.example.finalproject.Fragment.Content_Booking_Fragment.Dialog_Select_Night_Day;
import com.example.finalproject.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DiscountAvailableAdapter extends RecyclerView.Adapter<DiscountAvailableAdapter.DiscountViewHolder>{
    private List<DiscountAvailableModel> mDiscountAvailableModel;
    private Context mContext;
    private int  selectedTabPosition = 1;
    private OnUseClickListener onUseClickListener;

    public DiscountAvailableAdapter(Context mContext){
        this.mContext = mContext;

    }
    public void setData(List<DiscountAvailableModel> list){
        this.mDiscountAvailableModel = list;
        notifyDataSetChanged();
    }

    public interface OnUseClickListener {
        void onUseClick(String promotionID, int discountPercent);
    }

    public void setOnUseClickListener(OnUseClickListener listener) {
        this.onUseClickListener = listener;
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
        DiscountAvailableModel discountAvailableModel = mDiscountAvailableModel.get(position);
        if(discountAvailableModel == null){
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String typeDay = mContext.getResources().getString(R.string.typeDay);
        String typeNight = mContext.getResources().getString(R.string.typeNight);
        String startDay = dateFormat.format(discountAvailableModel.getStartDay());
        String endDay = dateFormat.format(discountAvailableModel.getEndDay());

        holder.tvName.setText(discountAvailableModel.getTitle());
        holder.tvPercent.setText(discountAvailableModel.getPercent()+"%");
        holder.tvDetail.setText(discountAvailableModel.getDetail());
        if(!discountAvailableModel.isTypeNight()){
            holder.isNight.setVisibility(View.GONE);
        }else{
            holder.isNight.setText(typeNight);
        }
        if(!discountAvailableModel.isTypeDay()){
            holder.isDay.setVisibility(View.GONE);
        }else{
            holder.isDay.setText(typeDay);
        }
        holder.tvDateStart.setText(startDay);
        holder.tvDateEnd.setText(endDay);
        holder.btnUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onUseClickListener != null) {
                    onUseClickListener.onUseClick(discountAvailableModel.getID(), discountAvailableModel.getPercent());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mDiscountAvailableModel != null){
            return mDiscountAvailableModel.size();
        }
        return 0;
    }

    public class DiscountViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName, tvPercent, tvDetail, isNight, isDay, tvDateStart, tvDateEnd;
        private Button btnUse;
        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPercent = itemView.findViewById(R.id.tvPercent);
            tvDetail = itemView.findViewById(R.id.tvDetail);
            isNight = itemView.findViewById(R.id.isNight);
            isDay = itemView.findViewById(R.id.isDay);
            tvDateStart = itemView.findViewById(R.id.tvDateStart);
            tvDateEnd = itemView.findViewById(R.id.tvDateEnd);
            btnUse = itemView.findViewById(R.id.btnUse);
        }
    }
}
