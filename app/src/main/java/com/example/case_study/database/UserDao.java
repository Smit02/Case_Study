package com.example.case_study.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.case_study.model.User;

import java.util.List;

@Dao
public interface UserDao {
//Query operation to display data from the table

    @Query("SELECT *FROM USER")
    LiveData<List<User>> getallDetails();

    //Insert operation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<User> user);
    //Delete operation  with id
    @Query("DELETE FROM USER WHERE id = :userId")
     void deleteByUserId(int userId);

}
