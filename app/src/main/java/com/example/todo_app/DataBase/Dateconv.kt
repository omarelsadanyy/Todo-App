package com.example.todo_app.DataBase

import androidx.room.TypeConverter
import java.util.*

class Dateconv {
    @TypeConverter
    fun fromdate(date:Date):Long{
        return date.time
    }
    @TypeConverter
    fun todate(time:Long):Date{
        return Date(time)
    }
}