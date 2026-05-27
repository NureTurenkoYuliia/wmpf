package com.example.myfirstapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfirstapplication.dao.ChatDao
import com.example.myfirstapplication.dao.ChatUserDao
import com.example.myfirstapplication.dao.CommentDao
import com.example.myfirstapplication.dao.FriendshipDao
import com.example.myfirstapplication.dao.MessageDao
import com.example.myfirstapplication.dao.PostDao
import com.example.myfirstapplication.dao.UserDao
import com.example.myfirstapplication.models.Chat
import com.example.myfirstapplication.models.ChatUser
import com.example.myfirstapplication.models.Comment
import com.example.myfirstapplication.models.Friendship
import com.example.myfirstapplication.models.Message
import com.example.myfirstapplication.models.Post
import com.example.myfirstapplication.models.User

@Database(
    entities = [
        User::class,
        Friendship::class,
        Post::class,
        Comment::class,
        Chat::class,
        ChatUser::class,
        Message::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun friendshipDao(): FriendshipDao
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
    abstract fun chatDao(): ChatDao
    abstract fun chatUserDao(): ChatUserDao
    abstract fun messageDao(): MessageDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "social_network_db"
                    ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}