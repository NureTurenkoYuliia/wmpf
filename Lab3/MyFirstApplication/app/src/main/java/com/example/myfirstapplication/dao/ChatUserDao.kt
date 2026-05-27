package com.example.myfirstapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myfirstapplication.models.ChatUser

@Dao
interface ChatUserDao {

    @Insert
    suspend fun insertChatUser(chatUser: ChatUser)

    @Query(
        """
        SELECT * FROM chat_users
        WHERE chatId = :chatId
        """
    )
    suspend fun getChatUsers(
        chatId: Int
    ): List<ChatUser>

    @Query(
        """
        SELECT userId
        FROM chat_users
        WHERE chatId = :chatId
        AND userId != :currentUserId
        LIMIT 1
        """
    )
    suspend fun getOtherUserId(
        chatId: Int,
        currentUserId: Int
    ): Int
}