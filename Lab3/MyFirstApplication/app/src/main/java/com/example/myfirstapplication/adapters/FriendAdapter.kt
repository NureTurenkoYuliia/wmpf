package com.example.myfirstapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.models.User

class FriendAdapter(

    private val friends: List<User>,

    private val onClick: (User) -> Unit

) : RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {

    class FriendViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val tvFriendName: TextView =
            view.findViewById(R.id.tvFriendName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_friend,
                    parent,
                    false
                )

        return FriendViewHolder(view)
    }

    override fun getItemCount(): Int {

        return friends.size
    }

    override fun onBindViewHolder(
        holder: FriendViewHolder,
        position: Int
    ) {

        val friend =
            friends[position]

        holder.tvFriendName.text =
            friend.userName

        holder.itemView.setOnClickListener {

            onClick(friend)
        }
    }
}