package com.example.todo_app.Fragments.ListAdapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.DataBase.Model.Todo
import com.example.todo_app.R

class TodoAdapter(var items:MutableList<Todo>?) :RecyclerView.Adapter<TodoAdapter.viewholder>() {
    class viewholder(itemview:View):RecyclerView.ViewHolder(itemview){
       val title:TextView=itemview.findViewById(R.id.title)
        val description:TextView=itemview.findViewById(R.id.description)
        val markasdone:ImageView=itemview.findViewById(R.id.mark_as_done)
        val delete :LinearLayout=itemview.findViewById(R.id.Delete_Icon)
        val edit :LinearLayout=itemview.findViewById(R.id.Edit_Icon)
        val view_line:View=itemview.findViewById(R.id.line)
        val text_mark_as_done :TextView=itemview.findViewById(R.id.mark_as_donee_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
       val view=LayoutInflater.from(parent.context)
           .inflate(R.layout.item_todo,parent,false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val item=items!!.get(position)
holder.title.setText(item.name)
holder.description.setText(item.details)

        holder.markasdone.setOnClickListener {
            holder.markasdone.isVisible=false
            holder.text_mark_as_done.setText("Done!")
            holder.title.setTextColor(Color.GREEN)
            holder.description.setTextColor(Color.GREEN)
            holder.view_line.setBackgroundColor(R.drawable.green_line)
            onitemlistClickedisdone?.onitemisdone(position,item)
        }
//        if(item.isDone==true){
//              holder.title.setTextColor(Color.GREEN)
//               holder.view_line.setBackgroundResource(R.color.green)
//                holder.markasdone.setBackgroundResource(R.color.green)
//        }
        holder.delete.setOnClickListener {
            onitemclicked?.onitemdeleted(position,item)
        }
        holder.edit.setOnClickListener {
           onitemclickedupdate?.onitemupdated(item)
        }
    }
    interface OnItemListClickedUpdate{

        fun onitemupdated(todo: Todo)
    }
interface OnItemListClicked{
    fun onitemdeleted(position: Int,todo: Todo)

}
    var onitemclickedupdate: OnItemListClickedUpdate?=null
    var onitemclicked: OnItemListClicked?=null
    override fun getItemCount(): Int = items?.size ?: 0
    fun changedata(newitems:MutableList<Todo>){
        items=newitems
        notifyDataSetChanged()
    }
    var onitemlistClickedisdone: OnItemListClickedisdone?=null
    interface OnItemListClickedisdone{
        fun onitemisdone(position: Int,todo: Todo)
    }
}