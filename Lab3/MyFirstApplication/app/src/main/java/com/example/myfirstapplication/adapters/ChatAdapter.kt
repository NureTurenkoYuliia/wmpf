package com.example.myfirstapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.models.Chat

class ChatAdapter(

    private val chats: List<Chat>,

    private val onClick: (Chat) -> Unit

) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val tvChatName: TextView =
            view.findViewById(R.id.tvChatName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_chat,
                    parent,
                    false
                )

        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {

        return chats.size
    }

    override fun onBindViewHolder(
        holder: ChatViewHolder,
        position: Int
    ) {

        val chat = chats[position]

        holder.tvChatName.text = chat.name

        holder.itemView.setOnClickListener {

            onClick(chat)
        }
    }
}