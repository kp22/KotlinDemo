package com.testapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.testapp.model.User;
import com.testapp.model.UserDao;

@Database(entities = {User.class,
}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
