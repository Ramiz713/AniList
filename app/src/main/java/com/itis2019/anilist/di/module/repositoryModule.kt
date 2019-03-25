package com.itis2019.anilist.di.module

import com.itis2019.anilist.api.JikanApiService
import com.itis2019.anilist.db.AnimeDao
import com.itis2019.anilist.repository.Repository
import com.itis2019.anilist.repository.RepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


fun repositoryModule() = Module("module name") {

    bind<Repository>() with singleton { provideRepository(instance(), instance()) }
}

private fun provideRepository(apiService: JikanApiService, animeDao: AnimeDao): Repository =
    RepositoryImpl(apiService, animeDao)
