package com.example.myfirstapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.models.Post

class PostAdapter(

    private val posts: List<Post>,

    private val getUserName: (Int) -> String,

    private val onCommentsClick: (Post) -> Unit

) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val tvUserName: TextView =
            view.findViewById(R.id.tvPostUserName)

        val tvContent: TextView =
            view.findViewById(R.id.tvPostContent)

        val tvCreatedAt: TextView =
            view.findViewById(R.id.tvPostCreatedAt)

        val btnComments: Button =
            view.findViewById(R.id.btnComments)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_post,
                    parent,
                    false
                )

        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {

        return posts.size
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {

        val post =
            posts[position]

        holder.tvUserName.text =
            getUserName(post.userId)

        holder.tvContent.text =
            post.content

        holder.tvCreatedAt.text =
            post.createdAt

        holder.btnComments.setOnClickListener {

            onCommentsClick(post)
        }
    }
}