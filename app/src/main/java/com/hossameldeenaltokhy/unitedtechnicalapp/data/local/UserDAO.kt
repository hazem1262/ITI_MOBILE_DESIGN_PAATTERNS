package com.hossameldeenaltokhy.unitedtechnicalapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hossameldeenaltokhy.unitedtechnicalapp.data.models.User

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(users:List<User>)

    @Query ("SELECT * FROM User")
    fun readUser(): List<User>
}