package com.example.finalproject.Fragment.Content_Booking_Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.Fragment.Content_Discount_Fragment.Content_Discount_Available;
import com.example.finalproject.Fragment.Content_Discount_Fragment.Content_Discount_History;
import com.example.finalproject.Fragment.Content_Discount_Fragment.DiscountFragmentPagerAdapter;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class BookingFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private BookingFragmentPagerAdapter bookingFragmentPagerAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        tabLayout = view.findViewById(R.id.tabLayoutBooking);
        viewPager = view.findViewById(R.id.viewPagerBooking);

        tabLayout.setupWithViewPager(viewPager);
        String title2 = getResources().getString(R.string.byNight);
        String title3 = getResources().getString(R.string.byDay);
        bookingFragmentPagerAdapter = new BookingFragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        bookingFragmentPagerAdapter.addFragmentBooking(new Content_Booking_Night(), title2);
        bookingFragmentPagerAdapter.addFragmentBooking(new Content_Booking_Day(), title3);
        viewPager.setAdapter(bookingFragmentPagerAdapter);

        Toolbar toolbar = view.findViewById(R.id.menuBooking);
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Đặt phòng");
        }

        return view;
    }

}