package com.hossameldeenaltokhy.unitedtechnicalapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hossameldeenaltokhy.unitedtechnicalapp.data.models.User


@Database (entities = [(User::class)], version = 1)
@TypeConverters(UsersTypeConverters::class)
abstract class UserDB : RoomDatabase(){
    abstract fun userDAO() : UserDAO
}