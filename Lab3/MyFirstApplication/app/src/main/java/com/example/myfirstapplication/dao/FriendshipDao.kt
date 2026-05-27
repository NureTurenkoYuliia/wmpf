package com.example.myfirstapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myfirstapplication.models.Friendship
import com.example.myfirstapplication.models.User

@Dao
interface FriendshipDao {

    @Insert
    suspend fun insertFriendship(friendship: Friendship)

    @Query(
        """
        SELECT * FROM users
        WHERE id IN (
            SELECT friendId
            FROM friendships
            WHERE userId = :userId
        )
        """
    )
    suspend fun getFriends(userId: Int): List<User>

    @Query(
        """
        SELECT EXISTS(
            SELECT 1 FROM friendships
            WHERE userId = :userId
            AND friendId = :friendId
        )
        """
    )
    suspend fun isFriendExists(
        userId: Int,
        friendId: Int
    ): Boolean
}