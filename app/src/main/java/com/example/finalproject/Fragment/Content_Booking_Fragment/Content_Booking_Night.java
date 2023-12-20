package com.example.finalproject.Fragment.Content_Booking_Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class Content_Booking_Night extends Fragment {

    private LinearLayout layout;
    private Context context;
    private TextView tvCheckIn;
    private TextView tvCheckOut;
    private Button btnSearchNight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content__booking__night, container, false);
        context = getContext();
        layout = view.findViewById(R.id.selectTimeLayout);
        tvCheckIn = view.findViewById(R.id.tvcheckIn);
        tvCheckOut = view.findViewById(R.id.tvcheckOut);
        btnSearchNight = view.findViewById(R.id.btnSearchNight);
        btnSearchNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchHotel.class);
                intent.putExtra("type", "Qua đêm");
                intent.putExtra("checkIn",tvCheckIn.getText().toString());
                intent.putExtra("checkOut",tvCheckOut.getText().toString());
                startActivity(intent);
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_select_night);

                Window window = dialog.getWindow();
                if(window == null){
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                WindowManager.LayoutParams windowAttributes = window.getAttributes();
                windowAttributes.gravity = Gravity.CENTER;
                window.setAttributes(windowAttributes);
                dialog.setCancelable(true);

                Button btnClose = dialog.findViewById(R.id.btnClose);
                Button btnApply = dialog.findViewById(R.id.btnApply);
                CalendarView selectedDateCalendar = dialog.findViewById(R.id.calendarView);
                TextView tvCheckInNight = dialog.findViewById(R.id.tvCheckInNight);
                TextView tvTimeCheckInNight = dialog.findViewById(R.id.tvTimeCheckInNight);
                TextView tvCheckOutNight = dialog.findViewById(R.id.tvCheckOutNight);
                ChipGroup chipGroupDay = dialog.findViewById(R.id.chipGroupDay);

                chipGroupDay.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
                    @Override
                    public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                        if(checkedIds.isEmpty()){
                            Chip chip = dialog.findViewById(R.id.tamgio);
                            tvTimeCheckInNight.setText(chip.getText());
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i : checkedIds){
                                Chip chip = dialog.findViewById(i);
                                stringBuilder.append(" ").append(chip.getText());
                            }
                            tvTimeCheckInNight.setText(stringBuilder.toString().replaceFirst(" ",""));
                        }
                    }
                });
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnApply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvCheckIn.setText(tvTimeCheckInNight.getText().toString() +" "+ tvCheckInNight.getText().toString());
                        tvCheckOut.setText(tvCheckOutNight.getText());

                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });

                selectedDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {



                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", Locale.getDefault());
                        String currentDay = sdf.format(selectedDate.getTime());
                        // Thêm 1 ngày vào ngày đã chọn
                        selectedDate.add(Calendar.DATE, 1);
                        String nextDay = sdf.format(selectedDate.getTime());
                        tvCheckInNight.setText(currentDay);
                        tvCheckOutNight.setText("12:00 "+nextDay);
                    }
                });


                dialog.show();
            }
        });

        return view;
    }

}