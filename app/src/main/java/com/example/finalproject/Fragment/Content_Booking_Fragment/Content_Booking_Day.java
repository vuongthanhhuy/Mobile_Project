package com.example.finalproject.Fragment.Content_Booking_Fragment;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;

public class Content_Booking_Day extends Fragment {

    private LinearLayout layout;
    private Context context;
    private TextView tvCheckIn;
    private TextView tvCheckOut;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content__booking__day, container, false);
        context = getContext();
        layout = view.findViewById(R.id.selectDateLayout);
        tvCheckIn = view.findViewById(R.id.tvcheckIn);
        tvCheckOut = view.findViewById(R.id.tvcheckOut);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_select_day);

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
                TextView tvCheckInDay = dialog.findViewById(R.id.tvCheckInDay);
                TextView tvCheckOutDay = dialog.findViewById(R.id.tvCheckOutDay);
                EditText edtNumDay = dialog.findViewById(R.id.numDay);
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnApply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvCheckIn.setText(tvCheckInDay.getText());
                        tvCheckOut.setText(tvCheckOutDay.getText());
                        if (edtNumDay.getText().toString().equals("")){
                            Toast.makeText(context, "Vui lòng chọn số ngày", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });

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
                dialog.show();
            }
        });

        return view;
    }
}