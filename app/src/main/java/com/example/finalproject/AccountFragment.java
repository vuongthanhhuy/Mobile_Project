package com.example.finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView changeLanguage = (TextView) view.findViewById(R.id.changeLanguage);
        Locale currentLocale = getResources().getConfiguration().locale;
        String currentLanguage = currentLocale.getLanguage();

        if ("vi".equals(currentLanguage)) {
            changeLanguage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.outline_language_24, 0, R.drawable.vietnam, 0);
        } else if ("en".equals(currentLanguage)) {
            changeLanguage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.outline_language_24, 0, R.drawable.united_states_of_america, 0);
        }

        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy Drawable bên phải của TextView
                Drawable rightDrawable = changeLanguage.getCompoundDrawables()[2]; // 2 là chỉ số của bên phải

                // Kiểm tra xem Drawable bên phải có tên là "@drawable/vietnam" hay không
                if (rightDrawable != null && rightDrawable.getConstantState() != null) {
                    if (rightDrawable.getConstantState().equals(getResources().getDrawable(R.drawable.vietnam).getConstantState())) {
                        // Thực hiện khởi động Activity với kết quả là "vn"
                        Intent intent = new Intent(getActivity(), ChangeLanguage.class);
                        intent.putExtra("result", "vn");
                        startActivityForResult(intent, 111);
                    } else if (rightDrawable.getConstantState().equals(getResources().getDrawable(R.drawable.united_states_of_america).getConstantState())) {
                        // Thực hiện khởi động Activity với kết quả là "en"
                        Intent intent = new Intent(getActivity(), ChangeLanguage.class);
                        intent.putExtra("result", "en");
                        startActivityForResult(intent, 111);
                    }
                }
            }


        });
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_account, container, false);
            return view;

    }
}