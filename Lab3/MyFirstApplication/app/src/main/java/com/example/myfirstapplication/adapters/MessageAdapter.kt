package com.example.myfirstapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.models.Message
import com.example.myfirstapplication.utils.SessionManager

class MessageAdapter(

    private val messages: List<Message>

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        private const val SENT = 1

        private const val RECEIVED = 2
    }

    class SentMessageViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val tvMessage: TextView =
            view.findViewById(R.id.tvSentMessage)
    }

    class ReceivedMessageViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val tvMessage: TextView =
            view.findViewById(R.id.tvReceivedMessage)
    }

    override fun getItemViewType(
        position: Int
    ): Int {

        return if (
            messages[position].userId ==
            SessionManager.currentUserId
        ) {
            SENT
        } else {
            RECEIVED
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return if (viewType == SENT) {

            val view =
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.item_message_sent,
                        parent,
                        false
                    )

            SentMessageViewHolder(view)

        } else {

            val view =
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.item_message_received,
                        parent,
                        false
                    )

            ReceivedMessageViewHolder(view)
        }
    }

    override fun getItemCount(): Int {

        return messages.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {

        val message = messages[position]

        if (holder is SentMessageViewHolder) {
            holder.tvMessage.text = message.text
        }

        if (holder is ReceivedMessageViewHolder) {
            holder.tvMessage.text = message.text
        }
    }
}