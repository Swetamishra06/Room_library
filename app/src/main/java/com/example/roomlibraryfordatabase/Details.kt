package com.example.roomlibraryfordatabase

import androidx.room.*


@Entity(tableName = "userDetails")
class Details(


    @ColumnInfo(name = "Name")
    var username: String,

    @ColumnInfo(name = " Phone_number")
    var usernumber: String,

)
{

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}