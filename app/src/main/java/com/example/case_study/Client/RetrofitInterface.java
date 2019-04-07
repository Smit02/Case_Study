package com.example.case_study.Client;

import com.example.case_study.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("users")
    public Call<List<User>> getAllPost();


}
