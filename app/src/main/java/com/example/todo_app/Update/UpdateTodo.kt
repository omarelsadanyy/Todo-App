package com.example.todo_app.Update

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import com.example.todo_app.DataBase.Model.Todo
import com.example.todo_app.DataBase.MyDateBase
import com.example.todo_app.R
import com.example.todo_app.cleartime
import com.example.todo_app.constants
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class UpdateTodo : AppCompatActivity() {
lateinit var title_layout:TextInputLayout
lateinit var details_layout:TextInputLayout
lateinit var choose_date:TextView
lateinit var add_update:Button
val calender=Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_todo)
           initviews()
        recive_data()
    }

    private fun initviews() {
         title_layout=findViewById(R.id.title_layout_update)
         details_layout=findViewById(R.id.details_layout_update)
        choose_date=findViewById(R.id.choose_date_update)
        add_update=findViewById(R.id.add_button_update)
        choose_date.setText(""+calender.get(Calendar.DAY_OF_MONTH)+"/"+(calender.get(Calendar.MONTH)+1)+"/"+calender.get(Calendar.YEAR))
         choose_date.setOnClickListener {
             showdatepicker()
         }
          add_update.setOnClickListener {
                     if(isValid()){
                      val title =title_layout.editText?.text.toString()
                         val details=details_layout.editText?.text.toString()
                        updateTodo(title,details)
                     }
          }
    }
lateinit var todo:Todo
    private fun updateTodo(title: String, details: String) {
         val task= Todo(todo.id,title,details,date=calender.cleartime().time )
        MyDateBase.getInstance(this).Tododao()
            .UpdateTodo(task)
        Toast.makeText(this,"Todo updated!", Toast.LENGTH_LONG).show()
        onBackPressed()
    }

    private fun showdatepicker() {
          val datePicker=DatePickerDialog(this,
          object :DatePickerDialog.OnDateSetListener{
              override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                     calender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                     calender.set(Calendar.MONTH,month)
                     calender.set(Calendar.YEAR,year)
                  choose_date.setText(""+dayOfMonth+"/"+(month+1)+"/"+year)
              }
          },calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }
    fun isValid(): Boolean {
        var isvalid = true
        if (title_layout.editText?.text.toString().isBlank()) {
            title_layout.error = "please enter todo title"
            isvalid = false
        } else {
            title_layout.error = null
        }
        if (details_layout.editText?.text.toString().isBlank()) {
            details_layout.error = "please enter todo details"
            isvalid = false
        } else {
            details_layout.error = null
        }
        return isvalid
    }
       fun recive_data(){
          todo =intent.getSerializableExtra(constants.Extra_todo) as Todo
            title_layout.editText?.setText(todo.name)
             details_layout.editText?.setText(todo.details)
       }
}