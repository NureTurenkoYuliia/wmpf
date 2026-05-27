package com.example.myfirstapplication.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myfirstapplication.R
import com.example.myfirstapplication.database.AppDatabase
import com.example.myfirstapplication.models.User
import com.example.myfirstapplication.utils.SessionManager
import kotlinx.coroutines.launch
import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class UserSelectionActivity : AppCompatActivity() {
    private lateinit var listUsers: ListView
    private lateinit var etUserName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etDateOfBirth: EditText
    private lateinit var etBio: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user_selection)

        listUsers = findViewById(R.id.listUsers)
        etUserName = findViewById(R.id.etUserName)
        etEmail = findViewById(R.id.etEmail)
        etDateOfBirth = findViewById(R.id.etDateOfBirth)
        val calendar = Calendar.getInstance()

        etDateOfBirth.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, // або context, якщо ви у Fragment
                { _, year, month, dayOfMonth ->
                    // Встановлюємо вибрану дату в календар
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    // Форматуємо дату у зручний вигляд (наприклад: 24.05.2026)
                    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                    etDateOfBirth.setText(dateFormat.format(calendar.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        etBio = findViewById(R.id.etBio)

        val btnCreateUser =
            findViewById<Button>(R.id.btnCreateUser)

        btnCreateUser.setOnClickListener {
            createUser()
        }

        listUsers.setOnItemClickListener { _, _, position, _ ->
            lifecycleScope.launch {

                val users =
                    AppDatabase
                        .getDatabase(this@UserSelectionActivity)
                        .userDao()
                        .getAllUsers()

                val user = users[position]

                SessionManager.currentUserId = user.id

                startActivity(
                    Intent(
                        this@UserSelectionActivity,
                        MainMenuActivity::class.java
                    )
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadUsers()
    }

    private fun loadUsers() {
        lifecycleScope.launch {

            val users =
                AppDatabase
                    .getDatabase(this@UserSelectionActivity)
                    .userDao()
                    .getAllUsers()

            val adapter =
                ArrayAdapter(
                    this@UserSelectionActivity,
                    android.R.layout.simple_list_item_1,
                    users.map { it.userName }
                )

            listUsers.adapter = adapter
        }
    }

    private fun createUser() {

        val userName =
            etUserName.text.toString()

        val email =
            etEmail.text.toString()

        val dateOfBirth =
            etDateOfBirth.text.toString()

        val bio =
            etBio.text.toString()

        if (
            userName.isBlank() ||
            email.isBlank()
        ) {

            Toast.makeText(
                this,
                "Fill required fields",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        lifecycleScope.launch {

            val user =
                User(
                    userName = userName,
                    email = email,
                    dateOfBirth = dateOfBirth,
                    bio = bio
                )

            AppDatabase
                .getDatabase(this@UserSelectionActivity)
                .userDao()
                .insertUser(user)

            etUserName.text.clear()
            etEmail.text.clear()
            etDateOfBirth.text.clear()
            etBio.text.clear()

            loadUsers()
        }
    }
}