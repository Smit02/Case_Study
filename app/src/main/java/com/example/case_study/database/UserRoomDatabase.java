package com.example.case_study.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.case_study.model.User;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    private static volatile UserRoomDatabase INSTANCE;

    public static UserRoomDatabase getDatabase(Context context){
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "user_database")
                            .build();
                }
            }
        }
        return INSTANCE;

    }

}
