package com.example.finalproject.Fragment.Content_Booking_Fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.HotelModel;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class ListRoomAdapter extends RecyclerView.Adapter<ListRoomAdapter.ListRoomViewHolder>{

    private List<ListRoomModel> mListRoom;
    private List<HotelModel> mHotelModel;
    private String checkIn, checkOut, name, phoneNumber;
    private boolean typeBooking = false;

    public void setData(List<ListRoomModel> list){
        this.mListRoom = list;
        notifyDataSetChanged();
    }
    public void updateCheckInOut(String checkIn, String checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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
        holder.typeOfRoom.setText(listRoom.getRoomName());
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        String priceRoom = numberFormat.format(listRoom.getPrice())+"đ";
        holder.price.setText(priceRoom);
        StringBuilder stringBuilder = new StringBuilder();
        if (listRoom.isWifi()) {
            stringBuilder.append("Wifi").append(" - ");
        }
        if (listRoom.isTV()) {
            stringBuilder.append("TV").append(" - ");
        }
        if (listRoom.isNetflix()) {
            stringBuilder.append("Netflix").append(" - ");
        }
        if (listRoom.isBathroom()) {
            stringBuilder.append("Bồn tắm đứng").append(" - ");
        }
        if (listRoom.isBathtub()) {
            stringBuilder.append("Bồn tắm").append(" - ");
        }
        if (listRoom.isKingbed()) {
            stringBuilder.append("Giường King").append(" - ");
        }

        // Remove the trailing " - " if it exists
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 3);
        }
        if (checkIn != null && checkIn.contains("12:00:00")) {
             typeBooking = true;
        }else{
             typeBooking = false;
        }
        holder.imageSlider.setImageList(listRoom.getImgRoom());
        holder.service.setText(stringBuilder.toString());
        holder.btnBookingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference hotelCollection = db.collection("hotel");
                hotelCollection.whereEqualTo("hotelID", listRoom.getHotelID()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot hotelDocument : task.getResult()) {
                                String hotelId = hotelDocument.getId();
                                String hotelName = hotelDocument.getString("hotelName");
                                String hotelAddress = hotelDocument.getString("hotelAddress");
                                CollectionReference roomCollection = hotelCollection.document(hotelId).collection("room");
                                roomCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot roomDocument : task.getResult()) {

                                                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                                String userId = currentUser.getUid();
                                                CollectionReference usersCollection = db.collection("users");
                                                usersCollection.document(userId).get()
                                                        .addOnSuccessListener(documentSnapshot -> {
                                                            if (documentSnapshot.exists()) {
                                                                name = documentSnapshot.getString("name");
                                                                phoneNumber = documentSnapshot.getString("phoneNumber");
                                                                String roomID = roomDocument.getString("roomID");
                                                                String roomName = roomDocument.getString("roomName");
                                                                Long roomPrice = roomDocument.getLong("roomPrice");
                                                                String userName = name;
                                                                String userPhoneNumber = phoneNumber;
                                                                Intent intent = new Intent(v.getContext(), Confirm_Payment.class);
                                                                intent.putExtra("hotelID",hotelId);
                                                                intent.putExtra("userID", userId);
                                                                intent.putExtra("roomID", roomID);
                                                                intent.putExtra("hotelName",hotelName);
                                                                intent.putExtra("hotelAddress",hotelAddress);
                                                                intent.putExtra("typeBooking",typeBooking);
                                                                intent.putExtra("roomName",roomName);
                                                                intent.putExtra("roomPrice",roomPrice);
                                                                intent.putExtra("checkIn",checkIn);
                                                                intent.putExtra("checkOut",checkOut);
                                                                intent.putExtra("userName",userName);
                                                                intent.putExtra("userPhoneNumber",userPhoneNumber);
                                                                Context context = v.getContext();
                                                                context.startActivity(intent);
                                                            } else {
                                                                Log.d("TAG", "No such document");
                                                            }
                                                        })
                                                        .addOnFailureListener(e -> {
                                                            Log.w("TAG", "Error getting document", e);
                                                        });


                                            }
                                        } else {
                                            Log.d("FirestoreQuery", "Error getting documents from room collection", task.getException());
                                        }
                                    }
                                });
                            }
                        } else {
                            Log.d("FirestoreQuery", "Error getting documents from hotel collection", task.getException());
                        }
                    }
                });
            }
        });
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
        private Button btnBookingRoom;
        private ImageSlider imageSlider;
        public ListRoomViewHolder(@NonNull View itemView) {
            super(itemView);

            typeOfRoom = itemView.findViewById(R.id.tvTypeOfRoom);
            price = itemView.findViewById(R.id.tvPrice);
            service = itemView.findViewById(R.id.tvService);
            btnBookingRoom = itemView.findViewById(R.id.btnBookingRoom);
            imageSlider = itemView.findViewById(R.id.image_slider);

        }
    }

}
