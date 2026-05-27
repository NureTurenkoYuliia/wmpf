package com.example.myfirstapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.models.Comment

class CommentAdapter(

    private val comments: List<Comment>,

    private val getUserName: (Int) -> String

) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val tvUserName: TextView =
            view.findViewById(R.id.tvCommentUserName)

        val tvContent: TextView =
            view.findViewById(R.id.tvCommentContent)

        val tvCreatedAt: TextView =
            view.findViewById(R.id.tvCommentCreatedAt)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_comment,
                    parent,
                    false
                )

        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {

        return comments.size
    }

    override fun onBindViewHolder(
        holder: CommentViewHolder,
        position: Int
    ) {

        val comment =
            comments[position]

        holder.tvUserName.text =
            getUserName(comment.userId)

        holder.tvContent.text =
            comment.content

        holder.tvCreatedAt.text =
            comment.createdAt
    }
}