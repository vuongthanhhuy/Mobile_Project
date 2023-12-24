package com.example.finalproject.Fragment.Content_Account_Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryBookingAdapter extends RecyclerView.Adapter<HistoryBookingAdapter.HistoryBookingViewHolder>{

    private List<HistoryBookingModel> mHistoryBooking;

    public void setData(List<HistoryBookingModel> list){
        this.mHistoryBooking = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_history_booking,parent,false);
        return new HistoryBookingAdapter.HistoryBookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryBookingViewHolder holder, int position) {
        HistoryBookingModel historyBooking = mHistoryBooking.get(position);
        if(historyBooking == null){
            return;
        }
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

        try {
            // Chuyển đổi chuỗi thành đối tượng Date
            Date date = originalFormat.parse(historyBooking.getBookingDate());

            // Định dạng lại để lấy ngày và giờ riêng biệt
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

            // Chuyển đổi Date thành chuỗi theo định dạng mới
            String dateStr = dateFormat.format(date);
            String timeStr = timeFormat.format(date);

            // Gán giá trị vào TextView hoặc holder
            holder.dateBooking.setText(dateStr);
            holder.timeBooking.setText(timeStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.bookingID.setText(historyBooking.getBookingID());
        holder.hotelName.setText(historyBooking.getHotelName());
        holder.roomName.setText(historyBooking.getRoomName());
        String price = historyBooking.getPrice()+"đ";
        holder.price.setText(price);
    }

    @Override
    public int getItemCount() {
        if(mHistoryBooking != null){
            return mHistoryBooking.size();
        }
        return 0;
    }

    class HistoryBookingViewHolder extends RecyclerView.ViewHolder{

        private TextView timeBooking, dateBooking, bookingID, hotelName, roomName, price;
        public HistoryBookingViewHolder(@NonNull View itemView) {
            super(itemView);

            timeBooking = itemView.findViewById(R.id.tvTimeBooking);
            dateBooking = itemView.findViewById(R.id.tvDateBooking);
            bookingID = itemView.findViewById(R.id.tvBookingID);
            hotelName = itemView.findViewById(R.id.tvHotelName);
            roomName = itemView.findViewById(R.id.tvRoomName);
            price = itemView.findViewById(R.id.tvPrice);
        }
    }
}
