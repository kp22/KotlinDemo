package com.testapp.main

import android.app.Application
import androidx.room.Room
import com.testapp.db.AppDatabase

class MyApp : Application() {

    companion object{
        private var context: MyApp? = null

        fun getAppDb(): AppDatabase? {
            return context?.let {
                Room.databaseBuilder<AppDatabase>(it, AppDatabase::class.java, "demo.db")
                    .build()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}