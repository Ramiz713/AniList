package com.itis2019.anilist.api

import com.itis2019.anilist.entitites.Page
import com.itis2019.anilist.entitites.anime.AnimeItem
import com.itis2019.anilist.entitites.manga.MangaItem
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApiService {

    @GET("top/anime/{page}")
    suspend fun getTopAnimeListAsync(@Path("page") page: Int): Page<AnimeItem>

    @GET("top/manga/{page}")
    suspend fun getTopMangaListAsync(@Path("page") page: Int): Page<MangaItem>

    @GET("anime/{id}")
    suspend fun getAnimeItemAsync(@Path("id") id: Int): AnimeItem
}
