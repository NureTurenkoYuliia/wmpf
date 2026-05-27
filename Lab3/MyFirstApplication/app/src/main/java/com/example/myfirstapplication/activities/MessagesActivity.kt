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
import com.example.myfirstapplication.adapters.MessageAdapter
import com.example.myfirstapplication.database.AppDatabase
import com.example.myfirstapplication.models.Message
import com.example.myfirstapplication.utils.SessionManager
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MessagesActivity : AppCompatActivity() {

    private lateinit var recyclerMessages: RecyclerView

    private lateinit var etMessage: EditText

    private var chatId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chat)

        chatId =
            intent.getIntExtra(
                "chatId",
                0
            )

        recyclerMessages =
            findViewById(R.id.recyclerMessages)

        etMessage =
            findViewById(R.id.etMessage)

        val btnSend =
            findViewById<Button>(R.id.btnSend)

        recyclerMessages.layoutManager =
            LinearLayoutManager(this)

        btnSend.setOnClickListener {

            sendMessage()
        }
    }

    override fun onResume() {
        super.onResume()

        loadMessages()
    }

    private fun loadMessages() {

        lifecycleScope.launch {

            val messages =
                AppDatabase
                    .getDatabase(this@MessagesActivity)
                    .messageDao()
                    .getMessagesByChat(chatId)

            recyclerMessages.adapter =
                MessageAdapter(messages)

            recyclerMessages.scrollToPosition(
                messages.size - 1
            )
        }
    }

    private fun sendMessage() {

        val text =
            etMessage.text.toString()

        if (text.isBlank()) {

            Toast.makeText(
                this,
                "Message is empty",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        lifecycleScope.launch {

            AppDatabase
                .getDatabase(this@MessagesActivity)
                .messageDao()
                .insertMessage(
                    Message(
                        chatId = chatId,
                        userId = SessionManager.currentUserId,
                        text = text,
                        createdAt = LocalDateTime.now().toString()
                    )
                )

            etMessage.text.clear()

            loadMessages()
        }
    }
}