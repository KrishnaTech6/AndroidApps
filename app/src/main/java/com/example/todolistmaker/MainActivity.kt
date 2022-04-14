package com.example.todolistmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var database: myDatabase
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Database
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java,"To_Do"
        ).build()


        todoAdapter= TodoAdapter(mutableListOf())

        rvTodolist.adapter = todoAdapter
        rvTodolist.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val todoTitle = etAddTodoTitle.text.toString()
            GlobalScope.launch {
                database.dao().insertTodo(Entity(0, "hello", false))
            }
            GlobalScope.launch {
                database.dao().updateTodo(Entity(0, "hello", false))
            }



            if (todoTitle.isNotEmpty()){

                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)

                etAddTodoTitle.text.clear()

            }
        }
        btnDeleteDone.setOnClickListener{
            todoAdapter.deleteDoneTodos()

        }




    }
}