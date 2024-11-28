package com.example.roomlibraryfordatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface DetailDAO {

    @Query("SELECT * FROM userdetails")
    fun getAllDetails(): List<Details>


    @Insert
    fun addTx( details : Details)

    @Update
    fun updateTx(details : Details)

    @Delete
    fun deleteTx(details : Details)

}
