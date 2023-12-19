package com.example.finalproject.Fragment.Content_Booking_Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class Dialog_Content_Night extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog__content__night, container, false);

        CalendarView selectedDateCalendar = view.findViewById(R.id.calendarView);
        TextView tvCheckInNight = view.findViewById(R.id.tvCheckInNight);
        TextView tvTimeCheckInNight = view.findViewById(R.id.tvTimeCheckInNight);
        TextView tvCheckOutNight = view.findViewById(R.id.tvCheckOutNight);
        ChipGroup chipGroupDay = view.findViewById(R.id.chipGroupDay);

        chipGroupDay.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                if(checkedIds.isEmpty()){
                    Chip chip = view.findViewById(R.id.tamgio);
                    tvTimeCheckInNight.setText(chip.getText());
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i : checkedIds){
                        Chip chip = view.findViewById(i);
                        stringBuilder.append(" ").append(chip.getText());
                    }
                    tvTimeCheckInNight.setText(stringBuilder.toString().replaceFirst(" ",""));
                }
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
        return view;
    }
}