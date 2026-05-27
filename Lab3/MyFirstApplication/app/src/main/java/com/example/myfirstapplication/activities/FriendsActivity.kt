package com.example.myfirstapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.adapters.FriendAdapter
import com.example.myfirstapplication.database.AppDatabase
import com.example.myfirstapplication.models.Chat
import com.example.myfirstapplication.models.ChatUser
import com.example.myfirstapplication.utils.SessionManager
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class FriendsActivity : AppCompatActivity() {

    private lateinit var recyclerFriends: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_friends)

        recyclerFriends =
            findViewById(R.id.recyclerFriends)

        recyclerFriends.layoutManager =
            LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()

        loadFriends()
    }

    private fun loadFriends() {

        lifecycleScope.launch {

            val friends =
                AppDatabase
                    .getDatabase(this@FriendsActivity)
                    .friendshipDao()
                    .getFriends(
                        SessionManager.currentUserId
                    )

            recyclerFriends.adapter =
                FriendAdapter(
                    friends
                ) { friend ->

                    openChat(friend.id, friend.userName)
                }
        }
    }

    private fun openChat(friendId: Int, friendName: String
    ) {
        lifecycleScope.launch {

            val db =
                AppDatabase
                    .getDatabase(this@FriendsActivity)

            val existingChat =
                db.chatDao()
                    .getPrivateChat(
                        SessionManager.currentUserId,
                        friendId
                    )

            val chatId: Int

            if (existingChat != null) {
                chatId = existingChat.id

            } else {
                chatId =
                    db.chatDao()
                        .insertChat(
                            Chat(
                                name = friendName,
                                type = "private",
                                createdAt = LocalDateTime.now().toString()
                            )
                        ).toInt()

                db.chatUserDao()
                    .insertChatUser(
                        ChatUser(
                            chatId = chatId,
                            userId =
                                SessionManager.currentUserId
                        )
                    )

                db.chatUserDao()
                    .insertChatUser(
                        ChatUser(
                            chatId = chatId,
                            userId = friendId
                        )
                    )
            }

            val intent =
                Intent(
                    this@FriendsActivity,
                    MessagesActivity::class.java
                )

            intent.putExtra(
                "chatId",
                chatId
            )

            startActivity(intent)
        }
    }
}