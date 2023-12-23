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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Content_Booking_Day extends Fragment {

    private LinearLayout layout;
    private Context context;
    private TextView tvCheckIn;
    private TextView tvCheckOut;
    private Button btnSearchDay;
    private String defaultTime;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content__booking__day, container, false);
        context = getContext();
        layout = view.findViewById(R.id.selectDateLayout);
        tvCheckIn = view.findViewById(R.id.tvcheckIn);
        tvCheckOut = view.findViewById(R.id.tvcheckOut);
        btnSearchDay = view.findViewById(R.id.btnSearchDay);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date day = calendar.getTime();
        Date currentDate = new Date();
        String currentDateStr = dateFormat.format(currentDate);

        defaultTime = " 12:00:00";
        String checkIn = currentDateStr + defaultTime;
        tvCheckIn.setText(checkIn);
        String nextDay = dateFormat.format(day)+ " 12:00:00";
        tvCheckOut.setText(nextDay);
        btnSearchDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SearchHotel.class);
                intent.putExtra("type", "Theo ngày");
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

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                // Lấy ngày hiện tại
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                Date day = calendar.getTime();
                Date currentDate = new Date();
                String currentDateStr = dateFormat.format(currentDate);

                String timeCheckInDay =currentDateStr + defaultTime;
                tvCheckInDay.setText(timeCheckInDay);
                String nextDay = dateFormat.format(day)+ " 12:00:00";
                tvCheckOutDay.setText(nextDay);

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
                        // Lấy giá trị từ EditText và chuyển đổi thành số nguyên
                        String numDaysStr = edtNumDay.getText().toString();
                        int numDays = Integer.parseInt(numDaysStr);


                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String currentDay = sdf.format(selectedDate.getTime());
                        selectedDate.add(Calendar.DATE, numDays);
                        String nextDay = sdf.format(selectedDate.getTime());
                        String timeCheckInDay = currentDay + " 12:00:00";
                        String timeCheckOutDay = nextDay + " 12:00:00";
                        tvCheckInDay.setText(timeCheckInDay);
                        tvCheckOutDay.setText(timeCheckOutDay);

                    }
                });
                dialog.show();
            }
        });

        return view;
    }
}