package com.example.case_study.Client;


public class ApiUtils {


    public static RetrofitInterface getServiceClass(){

        return RetrofitAPI.getRetrofit().create(RetrofitInterface.class);

    }


}
