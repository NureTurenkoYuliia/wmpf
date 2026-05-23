package com.example.myfirstapplication.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myfirstapplication.R
import com.example.myfirstapplication.api.RetrofitInstance
import kotlinx.coroutines.launch

class ApiActivity : AppCompatActivity() {

    private lateinit var btnLoadData: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_api)

        btnLoadData = findViewById(R.id.btnLoadData)
        tvResult = findViewById(R.id.tvResult)
        btnLoadData.setOnClickListener {

            lifecycleScope.launch {
                try {
                    val post = RetrofitInstance.api.getPost()

                    tvResult.text =getString(R.string.post_details, post.id, post.title, post.body)
                } catch (e: Exception) {
                    tvResult.text = getString(R.string.error_message, e.message ?: "Невідома помилка")
                }
            }
        }
    }
}