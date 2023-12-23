package com.example.finalproject.Fragment.Content_Discount_Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalproject.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;


public class DiscountFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    private DiscountFragmentPagerAdapter discountFragmentPagerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_discount, container, false);


        tabLayout = view.findViewById(R.id.tabLayoutDiscount);
        viewPager = view.findViewById(R.id.viewpagerDiscount);
        tabLayout.setupWithViewPager(viewPager);

        String title1 = getResources().getString(R.string.discountAvailableModel);
        String title2 = getResources().getString(R.string.discountHistory);
        discountFragmentPagerAdapter = new DiscountFragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        discountFragmentPagerAdapter.addFragmentDiscount(new Content_Discount_Available(), title1);
        discountFragmentPagerAdapter.addFragmentDiscount(new Content_Discount_History(), title2);
        viewPager.setAdapter(discountFragmentPagerAdapter);

        Toolbar toolbar = view.findViewById(R.id.menuDiscount);
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Ưu đãi");
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


}