package com.example.finalproject.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.CategoryHotel.Category;
import com.example.finalproject.CategoryHotel.CategoryAdapter;
import com.example.finalproject.Hotel.Hotel;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private Context mContext;
    public void setContext(Context context) {
        mContext = context;
    }
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Here, you can obtain mContext
        mContext = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcvCategory = view.findViewById(R.id.rcv_category);
        categoryAdapter = new CategoryAdapter(context);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL,false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        categoryAdapter.setData(getListCategory());
        rcvCategory.setAdapter(categoryAdapter);

        return view;
    }

    private List<Category> getListCategory(){
        List<Hotel> listHotel = new ArrayList<>();
        List<Category> listCategory = new ArrayList<>();

        listHotel.add(new Hotel("KS1","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));
        listHotel.add(new Hotel("KS2","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));
        listHotel.add(new Hotel("KS3","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));
        listHotel.add(new Hotel("KS4","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));

        listHotel.add(new Hotel("KS1","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));
        listHotel.add(new Hotel("KS2","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));
        listHotel.add(new Hotel("KS3","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));
        listHotel.add(new Hotel("KS4","quận 7",2,100,4,R.drawable.pexels_max_rahubovskiy_6782472,100));


        listCategory.add(new Category("Category 1", listHotel));
        listCategory.add(new Category("Category 2", listHotel));
        listCategory.add(new Category("Category 3", listHotel));
        listCategory.add(new Category("Category 4", listHotel));

        return listCategory;
    }
}