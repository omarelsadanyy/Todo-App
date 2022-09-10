package com.example.todo_app.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todo_app.Fragments.AddBottomFragment
import com.example.todo_app.R
import com.example.todo_app.Fragments.TodoListFragment
import com.example.todo_app.Fragments.settingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
lateinit var addbutton:FloatingActionButton
lateinit var bottom_navigation:BottomNavigationView
var settingsfragment= settingsFragment()
var listfragmnet= TodoListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
addbutton=findViewById(R.id.add_fragment)
        bottom_navigation=findViewById(R.id.bottom_navigation_view)
        bottom_navigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item->
         if(item.itemId== R.id.list_Icon){
            pushfragment(listfragmnet)
         }
            if(item.itemId== R.id.settings_Icon){
                pushfragment(settingsfragment)
            }
         return@OnItemSelectedListener true
        })
       bottom_navigation.selectedItemId= R.id.list_Icon
        addbutton.setOnClickListener {
        showAddbottom()
        }
    }

    private fun showAddbottom() {
    val addBottomFragment= AddBottomFragment()
    addBottomFragment.show(supportFragmentManager,"")
     addBottomFragment.ontodoAddedlistener=object : AddBottomFragment.TodoAddedListener {
         override fun ontodoadded() {
            if(listfragmnet.isVisible){
             listfragmnet.GettodosfromDB()
            }
         }
     }
    }

    private fun pushfragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment,"")
            .commit()
    }
}