package com.example.loginregisterapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class RegisterFragment extends Fragment {

    EditText user_first_name, user_last_name, user_email, user_password, user_checking_code;
    String first_name, last_name, email, password, reg_date, checking_code;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public void onAttach(Context context) {
        sharedPreferences = context.getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        CheckingFragment stopwatchFragment = new CheckingFragment();
        ft.replace(R.id.nestedFragmentContainer, stopwatchFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        user_first_name = (EditText) view.findViewById (R.id.user_first_name);
        user_last_name = (EditText) view.findViewById (R.id.user_last_name);
        user_email = (EditText) view.findViewById (R.id.user_email);
        user_password = (EditText) view.findViewById (R.id.user_password);
        //user_checking_code = (EditText) view.findViewById(R.id.nestedFragmentContainer).findViewById(R.id.checking_code);


        //user_data = intent.getStringArrayListExtra("user_data");


        Button btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = user_email.getText().toString();
                first_name = user_first_name.getText().toString();
                last_name = user_last_name.getText().toString();
                password = user_password.getText().toString();
                reg_date = new Date().toString();
                //checking_code = user_checking_code.getText().toString(); //getActivity().getIntent().getExtras().getString("checking_code");
                //Log.d("MY", checking_code);


                ContentValues userValues = new ContentValues();
                userValues.put("EMAIL", email);
                SQLiteOpenHelper userDatabaseHelper = new UserDatabaseHelper(getContext());

                if (email.length() > 3 && password.length() > 3 ) { //&& checking_code.equals("x7cmq5")
                    try {
                        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();

                        userValues.put("EMAIL", email);
                        userValues.put("FIRST_NAME", first_name);
                        userValues.put("LAST_NAME", last_name);
                        userValues.put("PASSWORD", password);
                        userValues.put("REG_DATE", reg_date);
                        db.insert("USER", null, userValues);
                        db.close();
                    } catch (SQLiteException e) {
                        Toast.makeText(getContext(), "Database unavailable", Toast.LENGTH_SHORT).show();
                    }


                    // User.users = new User[]{new User(email, first_name, last_name, password,reg_date)};
                    Toast.makeText(getContext(), "Registered", Toast.LENGTH_SHORT).show();

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentContainer, new LoginFragment());
                    ft.commit();
                } else {
                    Toast.makeText(getContext(), "Please, write your data correctly!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}