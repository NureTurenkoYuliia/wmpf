package com.example.myfirstapplication.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.adapters.UserAdapter
import com.example.myfirstapplication.database.AppDatabase
import com.example.myfirstapplication.models.Friendship
import com.example.myfirstapplication.utils.SessionManager
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class UsersActivity : AppCompatActivity() {

    private lateinit var recyclerUsers: RecyclerView

    private lateinit var etSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_users)

        recyclerUsers =
            findViewById(R.id.recyclerUsers)

        etSearch =
            findViewById(R.id.etSearchUsers)

        val btnSearch =
            findViewById<Button>(R.id.btnSearchUsers)

        recyclerUsers.layoutManager =
            LinearLayoutManager(this)

        btnSearch.setOnClickListener {

            searchUsers()
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
                    .getDatabase(this@UsersActivity)
                    .userDao()
                    .getAllUsers()
                    .filter {
                        it.id != SessionManager.currentUserId
                    }

            recyclerUsers.adapter =
                UserAdapter(
                    users
                ) { user ->

                    addFriend(user.id)
                }
        }
    }

    private fun searchUsers() {

        val query =
            etSearch.text.toString()

        lifecycleScope.launch {

            val users =
                AppDatabase
                    .getDatabase(this@UsersActivity)
                    .userDao()
                    .searchUsers(query)
                    .filter {
                        it.id != SessionManager.currentUserId
                    }

            recyclerUsers.adapter =
                UserAdapter(
                    users
                ) { user ->

                    addFriend(user.id)
                }
        }
    }

    private fun addFriend(
        friendId: Int
    ) {

        lifecycleScope.launch {

            val db =
                AppDatabase
                    .getDatabase(this@UsersActivity)

            val exists =
                db.friendshipDao()
                    .isFriendExists(
                        SessionManager.currentUserId,
                        friendId
                    )

            if (exists) {

                Toast.makeText(
                    this@UsersActivity,
                    "Already friends",
                    Toast.LENGTH_LONG
                ).show()

                return@launch
            }

            db.friendshipDao()
                .insertFriendship(
                    Friendship(
                        userId =
                            SessionManager.currentUserId,

                        friendId = friendId,

                        createdAt =
                            LocalDateTime.now().toString()
                    )
                )

            Toast.makeText(
                this@UsersActivity,
                "Friend added",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}