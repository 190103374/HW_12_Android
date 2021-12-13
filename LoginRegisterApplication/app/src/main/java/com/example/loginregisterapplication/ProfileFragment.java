package com.example.loginregisterapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    TextView user_email, reg_date, user_name;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        user_email = view.findViewById(R.id.profile_user_email);
        reg_date = view.findViewById(R.id.profile_reg_date);
        user_name = view.findViewById(R.id.profile_user_name);


        ArrayList<String> user_data = getActivity().getIntent().getExtras().getStringArrayList("user_data");
        //Bundle user_data = getArguments();
        user_email.setText(user_data.get(0));
        reg_date.setText(user_data.get(1));
        user_name.setText(user_data.get(2));
        Log.d("MY", user_data.toString());
        return view;
    }
}