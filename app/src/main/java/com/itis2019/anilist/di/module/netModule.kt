package com.itis2019.anilist.di.module

import com.itis2019.anilist.BuildConfig
import com.itis2019.anilist.api.JikanApiService
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun netModule () = Module("module name") {

    bind<Retrofit>() with singleton { provideRetrofit() }
    bind<JikanApiService>() with singleton { instance<Retrofit>().create(JikanApiService::class.java) }
}

private fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_BASE_URL)
        .build()
