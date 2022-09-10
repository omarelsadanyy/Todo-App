package com.example.todo_app.DataBase.Dao

import androidx.room.*
import com.example.todo_app.DataBase.Model.Todo
import java.util.*

@Dao
interface TodoDao {
    @Insert
    fun addTodo(todo: Todo)

    @Update
    fun UpdateTodo(todo: Todo)

    @Delete
    fun DeleteTodo(todo: Todo)

    @Query("select*from Todo")
    fun getallTodos():List<Todo>

    @Query("select * from Todo where date =:date ")
    fun getTodosByDate(date: Date): List<Todo>
}