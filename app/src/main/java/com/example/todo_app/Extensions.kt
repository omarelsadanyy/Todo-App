package com.example.todo_app

import java.util.*

fun Calendar.cleartime():Calendar{
    this.clear(Calendar.HOUR)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MILLISECOND)
return this
}