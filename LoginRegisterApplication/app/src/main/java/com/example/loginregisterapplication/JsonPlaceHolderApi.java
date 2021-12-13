package com.example.loginregisterapplication;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("api/characters")
    Call<List<Post>> getPosts();

    @GET("api/characters/{id}")
    Call<List<DetailPost>> getDetailPost(@Path("id") int char_id);
}
