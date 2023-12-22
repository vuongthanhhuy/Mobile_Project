package com.example.finalproject.Fragment.Content_Discount_Fragment;

import android.content.Context;
import android.util.Log;
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
        String typeDay = mContext.getResources().getString(R.string.typeDay);
        String typeNight = mContext.getResources().getString(R.string.typeNight);
        String startDay = dateFormat.format(discountAvailable.getStartDay());
        String endDay = dateFormat.format(discountAvailable.getEndDay());

        holder.tvName.setText(discountAvailable.getTitle());
        holder.tvPercent.setText(discountAvailable.getPercent()+"%");
        holder.tvDetail.setText(discountAvailable.getDetail());
        if(!discountAvailable.isTypeNight()){
            holder.isNight.setVisibility(View.GONE);
        }else{
            holder.isNight.setText(typeNight);
        }
        if(!discountAvailable.isTypeDay()){
            holder.isDay.setVisibility(View.GONE);
        }else{
            holder.isDay.setText(typeDay);
        }
            Log.d("asdas","222");
        holder.tvDateStart.setText(startDay);
        holder.tvDateEnd.setText(endDay);
        holder.btnUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mDiscountAvailable != null){
            return mDiscountAvailable.size();
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
