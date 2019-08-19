package com.itis2019.anilist.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.itis2019.anilist.entitites.anime.AnimeItem
import com.itis2019.anilist.entitites.manga.MangaItem

interface Repository {

    fun getAnimeLivePagedList(): LiveData<PagedList<AnimeItem>>

    fun getMangaLivePagedList(): LiveData<PagedList<MangaItem>>

    suspend fun getAnimeItem(id: Int): AnimeItem

    val animeResponseCallback: ResponseCallback

    val mangaResponseCallback: ResponseCallback
}
