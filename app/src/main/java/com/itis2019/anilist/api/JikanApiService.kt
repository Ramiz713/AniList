package com.itis2019.anilist.api

import com.itis2019.anilist.entitites.AnimeItem
import com.itis2019.anilist.entitites.Page
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApiService {

    @GET("top/anime/{page}")
    fun getTopAnimeList(@Path("page") page: Int): Single<Page<AnimeItem>>
}
