package com.example.finalproject.Fragment.Content_Account_Fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalproject.Fragment.Content_Account_Fragment.Content_Terms_Privacy.Terms_PrivacyPolicy;
import com.example.finalproject.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    private TextView tvTermsNprivacy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView changeLanguage = (TextView) view.findViewById(R.id.changeLanguage);
        Locale currentLocale = getResources().getConfiguration().locale;
        String currentLanguage = currentLocale.getLanguage();

        tvTermsNprivacy = view.findViewById(R.id.termsNprivacy);
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


        tvTermsNprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Terms_PrivacyPolicy.class);
                startActivity(intent);
            }
        });
            return view;

    }
}