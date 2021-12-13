package com.example.loginregisterapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CheckingFragment extends Fragment {

    EditText checking_code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checking, container, false);
        checking_code = (EditText) view.findViewById (R.id.checking_code);

//        Intent intent = new Intent(getContext(), RegisterFragment.class);
//        intent.putExtra("checking_code", "x7cmq5");
//        startActivity(intent);

        return view;
    }
}