package com.example.case_study.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.case_study.AppExecuter;
import com.example.case_study.Client.ApiUtils;
import com.example.case_study.activity.MainActivity;
import com.example.case_study.database.UserDao;
import com.example.case_study.database.UserRoomDatabase;
import com.example.case_study.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private UserDao userDao;
    Context context;
    public static final String TAG="Userrepository";

    private  static UserRepository instance;
    private static ArrayList<User> dataset=new ArrayList<>();
    LiveData<List<User>> data;
    public static  UserRepository getInstance(){
        if(instance==null){
           //  instance = new UserRepository();
        }
        return instance;
    }

    public UserRepository(Context application){
            this.context=application.getApplicationContext();
            UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
            userDao = db.userDao();
            data= userDao.getallDetails();


    }

    public LiveData<List<User>> getData(){
        dataFromNet();

        return data;

    }

    private void dataFromNet() {
    //return data from internet to above method
        ApiUtils.getServiceClass().getAllPost().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, final Response<List<User>> response) {

                if(response.isSuccessful())
                    AppExecuter.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {

                            userDao.insert(response.body());

                        }
                    });
               //  data.setValue(response.body());

                Log.d(TAG,"Successfully got the data");

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG,"Failed to get the data"+t);
            }
        });

    }

}
