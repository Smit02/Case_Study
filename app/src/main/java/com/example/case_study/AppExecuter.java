package com.example.case_study;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecuter {
    public static final Object LOCK=new Object();
    private static AppExecuter sIntance;
    private final Executor diskIO;


    public AppExecuter(Executor diskIO) {
        this.diskIO = diskIO;
    }
    public static AppExecuter getInstance(){
        if(sIntance==null){
         synchronized (LOCK){
             sIntance=new AppExecuter(Executors.newSingleThreadExecutor());
         }
        }
            return sIntance;
    }

    public Executor diskIO(){
        return diskIO;
    }

}
