package com.example.finalproject.Fragment.Content_Booking_Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.List;


public class ListRoomAdapter extends RecyclerView.Adapter<ListRoomAdapter.ListRoomViewHolder>{

    private List<ListRoomModel> mListRoom;

    public void setData(List<ListRoomModel> list){
        this.mListRoom = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ListRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_list_room,parent,false);
        return new ListRoomAdapter.ListRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRoomViewHolder holder, int position) {
        ListRoomModel listRoom = mListRoom.get(position);
        if(listRoom == null){
            return;
        }
        holder.typeOfRoom.setText(listRoom.getTypeOfRoom());
        holder.price.setText(listRoom.getPrice()+"đ");
        StringBuilder stringBuilder = new StringBuilder();
        if(listRoom.isWifi()){
            stringBuilder.append("Wifi").append(" - ");
        }
        if(listRoom.isTV()){
            stringBuilder.append("TV").append(" - ");
        }
        if(listRoom.isNetflix()){
            stringBuilder.append("Netflix").append(" - ");
        }
        if(listRoom.isBathtub()){
            stringBuilder.append("Bồn tắm").append(" - ");
        }
        if(listRoom.isKingbed()){
            stringBuilder.append("Giường King");
        }
        holder.service.setText(stringBuilder.toString());
    }

    @Override
    public int getItemCount() {
        if(mListRoom != null){
            return mListRoom.size();
        }
        return 0;
    }

    class ListRoomViewHolder extends RecyclerView.ViewHolder{

        private TextView typeOfRoom, price, service;
        public ListRoomViewHolder(@NonNull View itemView) {
            super(itemView);

            typeOfRoom = itemView.findViewById(R.id.tvTypeOfRoom);
            price = itemView.findViewById(R.id.tvPrice);
            service = itemView.findViewById(R.id.tvService);
        }
    }
}
