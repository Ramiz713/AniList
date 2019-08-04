package com.itis2019.anilist.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.itis2019.anilist.db.converters.*
import com.itis2019.anilist.entitites.AnimeItem
import com.itis2019.anilist.entitites.MangaItem

@Database(entities = [AnimeItem::class, MangaItem::class], version = 1)
@TypeConverters(
    AuthorsConverter::class,
    GenresConverter::class,
    RelatedConverter::class,
    SerializationConverter::class,
    SynonymsConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao

    abstract fun mangaDao(): MangaDao
}
