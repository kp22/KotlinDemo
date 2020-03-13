package com.testapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAll(): List<User?>?

    @Insert
    fun insertAll(vararg users: User?)

    @Update
    fun updateUser(users: User?)
}