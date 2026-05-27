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
import com.example.myfirstapplication.adapters.CommentAdapter
import com.example.myfirstapplication.database.AppDatabase
import com.example.myfirstapplication.models.Comment
import com.example.myfirstapplication.utils.SessionManager
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class CommentsActivity : AppCompatActivity() {

    private lateinit var recyclerComments: RecyclerView

    private lateinit var etComment: EditText

    private var postId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_comments)

        postId =
            intent.getIntExtra(
                "postId",
                0
            )

        recyclerComments =
            findViewById(R.id.recyclerComments)

        etComment =
            findViewById(R.id.etComment)

        val btnAddComment =
            findViewById<Button>(R.id.btnAddComment)

        recyclerComments.layoutManager =
            LinearLayoutManager(this)

        btnAddComment.setOnClickListener {

            addComment()
        }
    }

    override fun onResume() {
        super.onResume()

        loadComments()
    }

    private fun loadComments() {

        lifecycleScope.launch {

            val db = AppDatabase.getDatabase(this@CommentsActivity)

            val comments =
                db.commentDao()
                    .getCommentsByPost(postId)

            val users =
                db.userDao()
                    .getAllUsers()

            val userMap =
                users.associateBy(
                    { it.id },
                    { it.userName }
                )

            recyclerComments.adapter =
                CommentAdapter(
                    comments,

                    getUserName = { userId ->

                        userMap[userId] ?: "Unknown"
                    }
                )
        }
    }

    private fun addComment() {

        val content =
            etComment.text.toString()

        if (content.isBlank()) {

            Toast.makeText(
                this,
                "Comment is empty",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        lifecycleScope.launch {

            AppDatabase
                .getDatabase(this@CommentsActivity)
                .commentDao()
                .insertComment(
                    Comment(
                        userId =
                            SessionManager.currentUserId,

                        postId = postId,

                        content = content,

                        createdAt =
                            LocalDateTime.now().toString()
                    )
                )

            etComment.text.clear()

            loadComments()
        }
    }
}