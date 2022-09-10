package com.example.todo_app.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.todo_app.Main.MainActivity
import com.example.todo_app.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper())
            .postDelayed( {
                StartActivity()
            },2000)
    }

    private fun StartActivity() {
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    }
