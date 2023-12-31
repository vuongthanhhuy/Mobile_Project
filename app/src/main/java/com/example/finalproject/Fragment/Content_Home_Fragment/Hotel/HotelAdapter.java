package com.example.finalproject.Fragment.Content_Home_Fragment.Hotel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.Fragment.Content_Booking_Fragment.HotelDetails;
import com.example.finalproject.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<HotelModel> mHotelModel;
    private Context context;
    public void setData(List<HotelModel> list){
        if (mHotelModel == null) {
            mHotelModel = new ArrayList<>();
        }
        mHotelModel.addAll(list);
        notifyDataSetChanged();
    }

    // Interface để xử lý sự kiện click
    public interface OnHotelItemClickListener {
        void onHotelItemClick(int position, String hotelID);
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
        HotelModel hotelModel = mHotelModel.get(position);
        if (hotelModel == null) {
            return;
        }

        // Use data directly from the Hotel object
        holder.tvName.setText(hotelModel.getTitle());
        holder.tvAddressHotel.setText(hotelModel.getAddressHotel());
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        String priceString = numberFormat.format(hotelModel.getCost()) +"đ";
        holder.tvCost.setText(priceString);
        holder.tvRate.setText(hotelModel.getRate()+"");

        // Load symbolic image using Glide
        Glide.with(holder.imgHotel.getContext())
                .load(hotelModel.getImgHotel())
                .into(holder.imgHotel);

        // Set click listener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION && onHotelItemClickListener != null) {
                    // Pass both position and hotel title when an item is clicked
                    onHotelItemClickListener.onHotelItemClick(adapterPosition, hotelModel.getID());
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    CollectionReference hotelCollection = db.collection("hotel");

                    hotelCollection.whereEqualTo("hotelID", hotelModel.getID()).get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                String hotelID = document.getString("hotelID");
                                String hotelName = document.getString("hotelName");
                                String hotelAddress = document.getString("hotelAddress");

                                List<Boolean> hotelService = (List<Boolean>) document.get("hotelService");
                                if (hotelService != null) {
                                    boolean[] hotelServiceArray = new boolean[hotelService.size()];
                                    for (int i = 0; i < hotelService.size(); i++) {
                                        hotelServiceArray[i] = hotelService.get(i);
                                    }

                                    String hotelIntro = document.getString("hotelIntro");
                                    String checkInNight = document.getString("checkInNight");
                                    String checkInDay = document.getString("checkInDay");
                                    Long hotelPrice = document.getLong("hotelPrice");

                                    List<String> hotelImgRoom = (List<String>) document.get("hotelImageRoom");
                                    if (hotelImgRoom != null) {
                                        String[] hotelImgRoomArray = new String[hotelImgRoom.size()];
                                        for (int i = 0; i < hotelImgRoom.size(); i++) {
                                            hotelImgRoomArray[i] = hotelImgRoom.get(i);
                                        }

                                        Context context = view.getContext();
                                        Intent intent = new Intent(context, HotelDetails.class);
                                        intent.putExtra("hotelID",hotelID);
                                        intent.putExtra("hotelName", hotelName);
                                        intent.putExtra("hotelAddress", hotelAddress);
                                        intent.putExtra("hotelServiceArray", hotelServiceArray);
                                        intent.putExtra("hotelIntro", hotelIntro);
                                        intent.putExtra("hotelPrice", hotelPrice);
                                        intent.putExtra("checkInNight", checkInNight);
                                        intent.putExtra("checkInDay", checkInDay);
                                        intent.putExtra("hotelImgRoomArray", hotelImgRoomArray);
                                        context.startActivity(intent);
                                    } else {
                                        Log.d("FirestoreArray", "hotelImgRoom field is null for hotelName: " + hotelName);
                                    }
                                } else {
                                    Log.d("FirestoreArray", "hotelService field is null for hotelName: " + hotelName);
                                }
                            }
                        }
                    });

                }
            }
        });




    }




    @Override
    public int getItemCount() {
        if(mHotelModel != null){
            return mHotelModel.size();
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
