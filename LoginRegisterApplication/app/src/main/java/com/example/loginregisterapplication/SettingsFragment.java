package com.example.loginregisterapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SettingsFragment extends Fragment implements View.OnClickListener{

    TextView change_bg, share_app;
    LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        View view_2 = inflater.inflate(R.layout.activity_persons_page, container, false);

        change_bg = view.findViewById(R.id.change_bg);
        change_bg.setOnClickListener(this);

        share_app = view.findViewById(R.id.share_app);
        share_app.setOnClickListener(this);

        layout = view.findViewById(R.id.setting_layout);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.change_bg:
                Toast.makeText(getContext(), "Change background", Toast.LENGTH_SHORT).show();
               // AppCompatDelegate.getDefaultNightMode() = getCompactDelegate
                if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode
                            (AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode
                            (AppCompatDelegate.MODE_NIGHT_NO);
                }
                //layout.setBackgroundColor(Color.RED);
                break;
            case R.id.share_app:
                Toast.makeText(getContext(), "Share with other app", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String body = "Download this App";
                String sub = "http://play.google.com";
                intent.putExtra(Intent.EXTRA_TEXT, body);
                intent.putExtra(Intent.EXTRA_TEXT, sub);
                startActivity(Intent.createChooser(intent, "Share using"));
                break;
        }
    }
}