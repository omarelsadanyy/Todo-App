package com.example.todo_app.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.*
import com.example.todo_app.DataBase.Model.Todo
import com.example.todo_app.DataBase.MyDateBase
import com.example.todo_app.Fragments.ListAdapter.TodoAdapter
import com.example.todo_app.Update.UpdateTodo
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*

class TodoListFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }
    lateinit var recyclerview: RecyclerView
    lateinit var calenderview: MaterialCalendarView
    val adapter = TodoAdapter(null)
    lateinit var todoslist : List<Todo>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews()
    }



    override fun onResume() {
        super.onResume()
      GettodosfromDB()


    }

    var calendar = Calendar.getInstance()
    fun initviews() {
        recyclerview = requireView().findViewById(R.id.todos_recycler)
        calenderview = requireView().findViewById(R.id.Calender_view)
        calenderview.selectedDate = CalendarDay.today()
        recyclerview.adapter = adapter
        Item_deleted()
        Updateed()
        IsDone()
        calenderview.setOnDateChangedListener { widget, calendarDay, selected ->
            calendar.set(Calendar.DAY_OF_MONTH, calendarDay.day)
            calendar.set(Calendar.MONTH, calendarDay.month - 1)
            calendar.set(Calendar.YEAR, calendarDay.year)
          GettodosfromDB()
        }


    }

    private fun IsDone() {
       adapter.onitemlistClickedisdone=object : TodoAdapter.OnItemListClickedisdone {
           override fun onitemisdone(position: Int, todo: Todo) {
                       
           }
       }
    }

    fun Updateed(){
           adapter.onitemclickedupdate=object : TodoAdapter.OnItemListClickedUpdate {
               override fun onitemupdated(todo: Todo) {
               Item_Updated(todo)
               }
           }
       }
     fun Item_Updated(todo: Todo) {
       val intent=Intent(requireContext(), UpdateTodo::class.java)
      intent.putExtra(constants.Extra_todo,todo)
        startActivity(intent)
         activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun Item_deleted(){
      adapter.onitemclicked=object : TodoAdapter.OnItemListClicked {
          override fun onitemdeleted(position: Int, todo: Todo) {
            todoslist.toMutableList().removeAt(position)
             adapter.notifyItemRemoved(position)

              MyDateBase.getInstance(requireContext()).Tododao()
                  .DeleteTodo(todo)
              refreshTodoList();
              Toast.makeText(requireContext(),"Task deleted successfully !" , Toast.LENGTH_LONG)
                  .show()
          }
      }
}
    fun GettodosfromDB() {
         todoslist = MyDateBase.getInstance(requireContext()).Tododao()
            .getTodosByDate(calendar.cleartime().time)
        adapter.changedata(todoslist.toMutableList())
    }
    private fun refreshTodoList() {
        val list=MyDateBase.getInstance(requireContext()).Tododao().getTodosByDate(calendar.cleartime().time)
        adapter.changedata(list.toMutableList())
    }
}