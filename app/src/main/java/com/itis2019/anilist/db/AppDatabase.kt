package com.itis2019.anilist.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.itis2019.anilist.entitites.AnimeItem

@Database(entities = [AnimeItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao
}
