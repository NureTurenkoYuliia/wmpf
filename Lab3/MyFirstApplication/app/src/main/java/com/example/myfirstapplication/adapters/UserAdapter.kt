package com.example.myfirstapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.models.User

class UserAdapter(

    private val users: List<User>,

    private val onAddFriendClick: (User) -> Unit

) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val tvUserName: TextView =
            view.findViewById(R.id.tvUserName)

        val tvEmail: TextView =
            view.findViewById(R.id.tvEmail)

        val btnAddFriend: Button =
            view.findViewById(R.id.btnAddFriend)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_user,
                    parent,
                    false
                )

        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {

        return users.size
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {

        val user = users[position]

        holder.tvUserName.text =
            user.userName

        holder.tvEmail.text =
            user.email

        holder.btnAddFriend.setOnClickListener {

            onAddFriendClick(user)
        }
    }
}