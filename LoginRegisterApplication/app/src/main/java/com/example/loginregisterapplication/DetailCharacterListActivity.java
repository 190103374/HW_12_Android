package com.example.loginregisterapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailCharacterListActivity extends Activity {

    private static final String TAG = "CharacterListFragment";
    private static final String EXTRA_ID_MESS = "id";
    private TextView textViewName;
    private ImageView imageView;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_character_list);

        textViewName = findViewById(R.id.detail_name);
        imageView = findViewById(R.id.image_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.breakingbadapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        Call<List<DetailPost>> call = jsonPlaceHolderApi.getDetailPost(id);
        call.enqueue(new Callback<List<DetailPost>>() {
            @Override
            public void onResponse(Call<List<DetailPost>> call, Response<List<DetailPost>> response) {
                if (!response.isSuccessful()) {
                    textViewName.setText("Code: " + response.code());
                    return;
                }
                List<DetailPost> detailPosts = response.body();
                String content = "Fullname: " + detailPosts.get(0).getName() + "\n"
                        + "Birthdate: " + detailPosts.get(0).getDate() + "\n"
                        + "Occupation: " + detailPosts.get(0).getOccupation() + "\n"
                        + "status: " + detailPosts.get(0).getStatus() ;
                textViewName.setText(content);
                img = detailPosts.get(0).getImg();
                setImage(img);
            }


            @Override
            public void onFailure(Call<List<DetailPost>> call, Throwable t) {
                textViewName.setText(t.getMessage());
            }
        });
    }

    void setImage(String image) {
        Glide.with(this)
                .load(image)
                .into(imageView);
    }
}