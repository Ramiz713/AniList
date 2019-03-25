package com.itis2019.anilist.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.itis2019.anilist.db.AnimeDao
import com.itis2019.anilist.db.AppDatabase
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

fun roomModule() = Module("module name") {

    bind<AppDatabase>() with singleton { provideRoomDatabase(instance()) }
    bind<AnimeDao>() with singleton { provideAnimeDao(instance()) }
}

private fun provideRoomDatabase(context: Context): AppDatabase =
    Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "ani_list.db"
    ).build()

private fun provideAnimeDao(database: AppDatabase): AnimeDao = database.animeDao()
