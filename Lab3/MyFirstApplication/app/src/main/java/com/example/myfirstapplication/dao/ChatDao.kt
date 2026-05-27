package com.example.myfirstapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myfirstapplication.models.Chat

@Dao
interface ChatDao {

    @Insert
    suspend fun insertChat(chat: Chat): Long

    @Query(
        """
        SELECT chats.*
        FROM chats
        INNER JOIN chat_users
        ON chats.id = chat_users.chatId
        WHERE chat_users.userId = :userId
        """
    )
    suspend fun getUserChats(
        userId: Int
    ): List<Chat>

    @Query(
        """
        SELECT chats.*
        FROM chats
        INNER JOIN chat_users cu1
            ON chats.id = cu1.chatId
        INNER JOIN chat_users cu2
            ON chats.id = cu2.chatId
        WHERE chats.type = 'private'
        AND cu1.userId = :currentUserId
        AND cu2.userId = :friendId
        LIMIT 1
        """
    )
    suspend fun getPrivateChat(
        currentUserId: Int,
        friendId: Int
    ): Chat?

    @Query(
        """
        SELECT * FROM chats
        WHERE id = :chatId
        """
    )
    suspend fun getChatById(
        chatId: Int
    ): Chat
}