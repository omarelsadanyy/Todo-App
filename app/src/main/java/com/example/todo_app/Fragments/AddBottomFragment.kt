package com.example.todo_app.Fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import com.example.todo_app.DataBase.Model.Todo
import com.example.todo_app.DataBase.MyDateBase
import com.example.todo_app.R
import com.example.todo_app.cleartime
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class AddBottomFragment:BottomSheetDialogFragment(){
    lateinit var titlelayout:TextInputLayout
    lateinit var detailslayout:TextInputLayout
    lateinit var choosedate:TextView
    lateinit var addTodo:Button
    val calender=Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.addbottom_sheet_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews()
    }

    private fun initviews() {
        titlelayout=requireView().findViewById(R.id.title_layout)
        detailslayout=requireView().findViewById(R.id.details_layout)
        choosedate=requireView().findViewById(R.id.choose_date)
        addTodo= requireView().findViewById(R.id.add_button)
        choosedate.setText(""+calender.get(Calendar.DAY_OF_MONTH)+"/"+(calender.get(Calendar.MONTH)+1)+"/"+calender.get(Calendar.YEAR))
        choosedate.setOnClickListener {
            showDatepicker()
        }
        addTodo.setOnClickListener {
            if(isValid()){
                val title =titlelayout.editText?.text.toString()
                val details=detailslayout.editText?.text.toString()
                inserttodo(title,details)
            }
        }
    }

    private fun inserttodo(title: String, details: String) {
        val todo=Todo(name=title, details = details,date=calender.cleartime().time)
        MyDateBase.getInstance(requireContext()).Tododao().addTodo(todo)
        Toast.makeText(requireContext(),"Todo added!",Toast.LENGTH_LONG)
            .show()
        ontodoAddedlistener?.ontodoadded()
      dismiss()
    }
var ontodoAddedlistener: TodoAddedListener?=null
    interface TodoAddedListener{
    fun ontodoadded()
    }
    fun isValid():Boolean{
         var isvalid=true;
         if(titlelayout.editText?.text.toString().isBlank()){
           titlelayout.error="please enter the title!"
             isvalid=false;
         }else{
             titlelayout.error=null
         }
         if(detailslayout.editText?.text.toString().isBlank()){
             detailslayout.error="please enter the title!"
             isvalid=false;
         }else{
             detailslayout.error=null
         }
         return isvalid
     }
    private fun showDatepicker() {
        val Datepicker=DatePickerDialog(requireContext()
        ,object :DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    calender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                    calender.set(Calendar.MONTH,month)
                    calender.set(Calendar.YEAR,year)
                    choosedate.setText(""+dayOfMonth+"/"+(month+1)+"/"+year)
                }
        },calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH)
        )
          Datepicker.show()
    }
}