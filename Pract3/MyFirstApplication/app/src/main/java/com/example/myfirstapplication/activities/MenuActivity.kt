package com.example.myfirstapplication.activities

import android.content.Intent
import com.example.myfirstapplication.activities.MainActivity
import com.example.myfirstapplication.activities.RecipeActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapplication.R
import kotlin.jvm.java

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)

        findViewById<Button>(R.id.btnTask1)
            .setOnClickListener {

                startActivity(
                    Intent(this, MainActivity::class.java)
                )
            }

        findViewById<Button>(R.id.btnTask2)
            .setOnClickListener {

                startActivity(
                    Intent(this, TodoActivity::class.java)
                )
            }

        findViewById<Button>(R.id.btnTask3)
            .setOnClickListener {

                startActivity(
                    Intent(this, ApiActivity::class.java)
                )
            }

        findViewById<Button>(R.id.btnTask4)
            .setOnClickListener {

                startActivity(
                    Intent(this, RecipeActivity::class.java)
                )
            }
    }
}