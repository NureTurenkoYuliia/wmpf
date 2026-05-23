package com.example.myfirstapplication.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.adapters.TaskAdapter
import com.example.myfirstapplication.models.Task

class TodoActivity : AppCompatActivity() {
    private lateinit var etTask: EditText
    private lateinit var btnAddTask: Button
    private lateinit var recyclerTasks: RecyclerView
    private lateinit var adapter: TaskAdapter
    private val taskList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_todo)

        etTask = findViewById(R.id.etTask)
        btnAddTask = findViewById(R.id.btnAddTask)
        recyclerTasks = findViewById(R.id.recyclerTasks)

        adapter = TaskAdapter(taskList)

        recyclerTasks.layoutManager = LinearLayoutManager(this)
        recyclerTasks.adapter = adapter
        btnAddTask.setOnClickListener {

            val taskTitle = etTask.text.toString()

            if (taskTitle.isNotEmpty()) {
                taskList.add(Task(taskTitle, false))

                adapter.notifyDataSetChanged()
                etTask.text.clear()
            }
        }
    }
}