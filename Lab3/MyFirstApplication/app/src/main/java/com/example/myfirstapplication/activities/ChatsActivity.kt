package com.example.myfirstapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.adapters.ChatAdapter
import com.example.myfirstapplication.database.AppDatabase
import com.example.myfirstapplication.utils.SessionManager
import com.example.myfirstapplication.models.Chat
import kotlinx.coroutines.launch

class ChatsActivity : AppCompatActivity() {

    private lateinit var recyclerChats: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chats)

        recyclerChats =
            findViewById(R.id.recyclerChats)

        recyclerChats.layoutManager =
            LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()

        loadChats()
    }

    private fun loadChats() {

        lifecycleScope.launch {

            val db = AppDatabase
                    .getDatabase(this@ChatsActivity)

            val chats = db.chatDao()
                    .getUserChats(
                        SessionManager.currentUserId
                    )

            val updatedChats = mutableListOf<Chat>()

            for (chat in chats) {

                if (chat.type == "private") {
                    val otherUserId =
                        db.chatUserDao()
                            .getOtherUserId(
                                chat.id,
                                SessionManager.currentUserId
                            )

                    val otherUser = db.userDao()
                            .getUserById(otherUserId)

                    updatedChats.add(
                        chat.copy(
                            name = otherUser.userName
                        )
                    )

                } else {
                    updatedChats.add(chat)
                }
            }

            recyclerChats.adapter =
                ChatAdapter(updatedChats) { chat ->
                    val intent =
                        Intent(
                            this@ChatsActivity,
                            MessagesActivity::class.java
                        )

                    intent.putExtra(
                        "chatId",
                        chat.id
                    )

                    startActivity(intent)
                }
        }
    }
}