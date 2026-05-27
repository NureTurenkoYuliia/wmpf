package com.example.myfirstapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myfirstapplication.models.User

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query(
        """
        SELECT * FROM users
        WHERE userName LIKE '%' || :query || '%'
        """
    )
    suspend fun searchUsers(query: String): List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): User
}