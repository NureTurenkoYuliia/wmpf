package com.example.myfirstapplication.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.adapters.PostAdapter
import com.example.myfirstapplication.database.AppDatabase
import com.example.myfirstapplication.models.Post
import com.example.myfirstapplication.utils.SessionManager
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class PostsActivity : AppCompatActivity() {

    private lateinit var recyclerPosts: RecyclerView

    private lateinit var etPost: EditText

    private lateinit var etSearchPosts: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_posts)

        recyclerPosts =
            findViewById(R.id.recyclerPosts)

        etPost =
            findViewById(R.id.etPost)

        etSearchPosts =
            findViewById(R.id.etSearchPosts)

        val btnCreatePost =
            findViewById<Button>(R.id.btnCreatePost)

        val btnSearchPosts =
            findViewById<Button>(R.id.btnSearchPosts)

        recyclerPosts.layoutManager =
            LinearLayoutManager(this)

        btnCreatePost.setOnClickListener {

            createPost()
        }

        btnSearchPosts.setOnClickListener {

            searchPosts()
        }
    }

    override fun onResume() {
        super.onResume()

        loadPosts()
    }

    private fun loadPosts() {

        lifecycleScope.launch {

            val db =
                AppDatabase
                    .getDatabase(this@PostsActivity)

            val posts =
                db.postDao()
                    .getAllPosts()

            val users =
                db.userDao()
                    .getAllUsers()

            val userMap =
                users.associateBy(
                    { it.id },
                    { it.userName }
                )

            recyclerPosts.adapter =
                PostAdapter(
                    posts,

                    getUserName = { userId ->

                        userMap[userId] ?: "Unknown"
                    },

                    onCommentsClick = { post ->

                        val intent =
                            Intent(
                                this@PostsActivity,
                                CommentsActivity::class.java
                            )

                        intent.putExtra(
                            "postId",
                            post.id
                        )

                        startActivity(intent)
                    }
                )
        }
    }

    private fun createPost() {

        val content =
            etPost.text.toString()

        if (content.isBlank()) {

            Toast.makeText(
                this,
                "Post is empty",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        lifecycleScope.launch {

            AppDatabase
                .getDatabase(this@PostsActivity)
                .postDao()
                .insertPost(
                    Post(
                        userId =
                            SessionManager.currentUserId,

                        content = content,

                        createdAt =
                            LocalDateTime.now().toString()
                    )
                )

            etPost.text.clear()

            loadPosts()
        }
    }

    private fun searchPosts() {

        val query = etSearchPosts.text.toString()

        lifecycleScope.launch {

            val db = AppDatabase
                    .getDatabase(this@PostsActivity)

            val posts = db.postDao()
                    .searchPosts(query)

            val users =
                db.userDao()
                    .getAllUsers()

            val userMap =
                users.associateBy(
                    { it.id },
                    { it.userName }
                )

            recyclerPosts.adapter =
                PostAdapter(
                    posts,

                    getUserName = { userId ->

                        userMap[userId] ?: "Unknown"
                    },

                    onCommentsClick = { post ->

                        val intent =
                            Intent(
                                this@PostsActivity,
                                CommentsActivity::class.java
                            )

                        intent.putExtra(
                            "postId",
                            post.id
                        )

                        startActivity(intent)
                    }
                )
        }
    }
}