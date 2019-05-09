package com.itis2019.anilist.api

import com.itis2019.anilist.entitites.AnimeItem
import com.itis2019.anilist.entitites.Page
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApiService {

    @GET("top/anime/{page}")
    fun getTopAnimeListAsync(@Path("page") page: Int): Deferred<Page<AnimeItem>>
}
