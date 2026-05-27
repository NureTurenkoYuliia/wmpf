package com.example.myfirstapplication.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapplication.R

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_menu)

        findViewById<Button>(R.id.btnUsers)
            .setOnClickListener {

                startActivity(
                    Intent(this, UsersActivity::class.java)
                )
            }

        findViewById<Button>(R.id.btnFriends)
            .setOnClickListener {

                startActivity(
                    Intent(this, FriendsActivity::class.java)
                )
            }

        findViewById<Button>(R.id.btnPosts)
            .setOnClickListener {

                startActivity(
                    Intent(this, PostsActivity::class.java)
                )
            }

        findViewById<Button>(R.id.btnChats)
            .setOnClickListener {

                startActivity(
                    Intent(this, ChatsActivity::class.java)
                )
            }
    }
}