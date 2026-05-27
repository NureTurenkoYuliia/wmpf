package com.example.myfirstapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myfirstapplication.models.Post

@Dao
interface PostDao {

    @Insert
    suspend fun insertPost(post: Post)

    @Query(
        """
        SELECT * FROM posts
        ORDER BY id DESC
        """
    )
    suspend fun getAllPosts(): List<Post>

    @Query(
        """
        SELECT * FROM posts
        WHERE content LIKE '%' || :query || '%'
        ORDER BY id DESC
        """
    )
    suspend fun searchPosts(query: String): List<Post>
}