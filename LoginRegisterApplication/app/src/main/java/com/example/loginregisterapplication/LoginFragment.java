package com.example.loginregisterapplication;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class LoginFragment extends Fragment {

//    public final String Email = "email";
//    public final String Password = "password";

    EditText user_email, user_password;
    Button btn_login, btn_register;
    String email = "", password = "";
    ArrayList<String> user_data = new ArrayList<>();

    SharedPreferences sharedPreferences;

    int i = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        user_email = (EditText) view.findViewById (R.id.user_email);
        user_password = (EditText) view.findViewById (R.id.user_password);

        btn_login = (Button) view.findViewById (R.id.btn_login);
        btn_register = (Button) view.findViewById(R.id.btn_register);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = user_email.getText().toString();
                password = user_password.getText().toString();

                try {
                    SQLiteOpenHelper userDatabaseHelper = new UserDatabaseHelper(getContext());
                    SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
                    Cursor cursor = db.query ("USER",
                            new String[] {"EMAIL", "FIRST_NAME", "LAST_NAME", "PASSWORD", "REG_DATE"},
                            "EMAIL = ? AND PASSWORD = ?",
                            new String[] {email, password},
                            null, null, null);

                    Log.d("MY", "OK");

                    if (cursor.moveToFirst()) {
                       // Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
                        String user_email = cursor.getString(0);
                        String user_password = cursor.getString(3);
                        String user_reg_date = cursor.getString(4);
                        String user_full_name = cursor.getString(1) + " " + cursor.getString(2);

                        user_data.add(0, user_email);
                        user_data.add(1, user_password);
                        user_data.add(2, user_reg_date);
                        user_data.add(3, user_full_name);
                        Log.d("MY", user_data.toString());

                        Intent intent_data = new Intent(getContext(), ProfileFragment.class);
                        intent_data.putStringArrayListExtra("user_data", user_data);
                        startActivity(intent_data);
                    }
                    Intent intent = new Intent(getActivity(), PersonsPage.class);
                    startActivity(intent);
                    cursor.close();
                    db.close();



                   // } else if (!email.equals(cursor.getString(0)) || !password.equals(cursor.getString(3)))
                        //{
                        //Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

                  //  }


                } catch (SQLiteException e) {
                    Log.i("MY", e.toString());
                   // Toast.makeText(getContext(), "Database unavailable", Toast.LENGTH_SHORT).show();
                }

               // Log.d("MY", user_data.toString());
                //User user = User.users[i];
//                if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
//                    Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();


//                    user_data.add(0, user.getEmail());
//                    user_data.add(1, user.getRegDate());
//                    user_data.add(2, user.getFullName());

//                    Intent intent = new Intent(getActivity(), PersonsPage.class);
//                    intent.putStringArrayListExtra("user_data", user_data);
//                    startActivity(intent);
//
//                } else if (!email.equals(user.getEmail()) || !password.equals(user.getPassword()))
//                    {
//                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
//
//                }
            }

        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new RegisterFragment());
                ft.commit();
            }
        });

        return view;
    }


}