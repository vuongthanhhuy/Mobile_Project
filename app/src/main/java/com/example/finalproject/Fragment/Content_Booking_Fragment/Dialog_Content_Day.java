package com.example.finalproject.Fragment.Content_Booking_Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;

public class Dialog_Content_Day extends Fragment {
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog__content__day, container, false);
        context = getContext();
        CalendarView selectedDateCalendar = view.findViewById(R.id.calendarView);
        TextView tvCheckInDay = view.findViewById(R.id.tvCheckInDay);
        TextView tvCheckOutDay = view.findViewById(R.id.tvCheckOutDay);
        EditText edtNumDay = view.findViewById(R.id.numDay);

        selectedDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (edtNumDay.getText().toString().equals("")){
                    Toast.makeText(context, "Vui lòng chọn số ngày", Toast.LENGTH_SHORT).show();
                    return;
                }
                String selectedCheckInDate = dayOfMonth + "/" + (month + 1);
                String selectedCheckOutDate = (dayOfMonth + Integer.parseInt(edtNumDay.getText().toString())) + "/" + (month + 1);
                tvCheckInDay.setText(selectedCheckInDate);
                tvCheckOutDay.setText(selectedCheckOutDate);


            }
        });
        return view;
    }
}