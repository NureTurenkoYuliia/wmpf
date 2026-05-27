package com.example.myfirstapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myfirstapplication.models.Comment

@Dao
interface CommentDao {

    @Insert
    suspend fun insertComment(comment: Comment)

    @Query(
        """
        SELECT * FROM comments
        WHERE postId = :postId
        ORDER BY id DESC
        """
    )
    suspend fun getCommentsByPost(
        postId: Int
    ): List<Comment>
}