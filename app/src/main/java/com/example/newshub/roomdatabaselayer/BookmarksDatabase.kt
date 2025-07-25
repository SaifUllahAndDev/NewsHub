package com.example.newshub.roomdatabaselayer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookmarkedArticle::class] , version = 2 )
abstract class BookmarksDatabase : RoomDatabase() {
    abstract fun bookmarkDao() : BookmarkDao

    companion object {
        @Volatile
        var INSTANCE : BookmarksDatabase? = null

        fun getDatabase(context: Context) : BookmarksDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookmarksDatabase::class.java ,
                    "bookmarks_database_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}