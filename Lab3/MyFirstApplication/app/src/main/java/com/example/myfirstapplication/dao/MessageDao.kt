package com.example.myfirstapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myfirstapplication.models.Message

@Dao
interface MessageDao {

    @Insert
    suspend fun insertMessage(message: Message)

    @Query(
        """
        SELECT * FROM messages
        WHERE chatId = :chatId
        ORDER BY id ASC
        """
    )
    suspend fun getMessagesByChat(
        chatId: Int
    ): List<Message>
}