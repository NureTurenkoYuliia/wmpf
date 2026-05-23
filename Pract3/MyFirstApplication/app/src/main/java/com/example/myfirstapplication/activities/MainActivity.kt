package com.example.myfirstapplication.activities

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnMessage = findViewById<Button>(R.id.btnMessage)

        btnMessage.setOnClickListener {
            Toast.makeText(
                this,
                "Вітаємо з першим додатком на Kotlin!",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}