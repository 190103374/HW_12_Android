package com.example.loginregisterapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class CharacterListFragment extends Fragment implements Adapter.OnClickItemListener {

    private static final String TAG = "CharacterListFragment";
    private TextView textViewName;
    private TextView textViewDate;
    RecyclerView recyclerView;
    List<Post> postList = new ArrayList<>();
    List<DetailPost> detailPostList = new ArrayList<>();
    JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textViewDetailName;
    List<Post> posts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_character_list, container, false);
        textViewName = view.findViewById(R.id.text_view_name);
        textViewDate = view.findViewById(R.id.text_view_date);

        textViewDetailName = view.findViewById(R.id.detail_name);

        recyclerView = view.findViewById(R.id.recycler_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.breakingbadapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        getPosts();
        return view;
    }

    private void getPosts() {
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewName.setText("Code: " + response.code());
                    return;
                }

                posts = response.body();
                for (Post post : posts) {
                    postList.add(post);
                }

                PutDataIntoRecyclerView(postList);
                textViewDetailName.setText("List of your characters " + posts.size());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
              //  textViewName.setText(t.getMessage());
            }
        });
    }

    private void PutDataIntoRecyclerView(List<Post> postList) {
        Adapter adapter = new Adapter(getContext(), postList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickItem(int position) {
        Log.d(TAG, "onClickItem: clicked." + postList.get(position).getChar_id());

        Intent intent = new Intent(this.getContext(), DetailCharacterListActivity.class);
        intent.putExtra("id",  postList.get(position).getChar_id());
        startActivity(intent);
    }
}