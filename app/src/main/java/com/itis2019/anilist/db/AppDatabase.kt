package com.itis2019.anilist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itis2019.anilist.db.converters.*
import com.itis2019.anilist.entitites.anime.AnimeItem
import com.itis2019.anilist.entitites.manga.MangaItem

@Database(entities = [AnimeItem::class, MangaItem::class], version = 1)
@TypeConverters(
    AuthorsConverter::class,
    GenresConverter::class,
    RelatedConverter::class,
    SerializationConverter::class,
    SynonymsConverter::class,
    LicensorsConverter::class,
    ProducersConverter::class,
    StudioConverter::class,
    PropConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao

    abstract fun mangaDao(): MangaDao
}
