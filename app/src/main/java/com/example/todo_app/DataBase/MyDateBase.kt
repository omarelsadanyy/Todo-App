package com.example.todo_app.DataBase

import android.content.Context
import androidx.room.*
import com.example.todo_app.DataBase.Dao.TodoDao
import com.example.todo_app.DataBase.Model.Todo

@Database(entities = [Todo::class], version = 1)
@TypeConverters(Dateconv::class)

abstract class MyDateBase:RoomDatabase() {
abstract fun Tododao():TodoDao
companion object{
private val DateBase_Name="todo-database"
    private var myDatabase:MyDateBase?=null

    fun getInstance(context: Context):MyDateBase{
             if(myDatabase==null){
              myDatabase= Room.databaseBuilder(
              context,MyDateBase::class.java, DateBase_Name
              )
                  .fallbackToDestructiveMigration()
                  .allowMainThreadQueries()
                  .build()
             }
        return myDatabase!!
    }
}
}