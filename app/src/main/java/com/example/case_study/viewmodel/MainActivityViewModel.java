package com.example.case_study.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.case_study.model.User;
import com.example.case_study.repositories.UserRepository;


import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private LiveData<List<User>> muserdata;
    private UserRepository repository;


    public MainActivityViewModel (Application application) {
        super(application);
        repository = new UserRepository(application);
        muserdata = repository.getData();
    }


    public LiveData<List<User>> getUserData(){
        return muserdata;
    }

}
