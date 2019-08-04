package com.itis2019.anilist.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.itis2019.anilist.entitites.AnimeItem
import com.itis2019.anilist.entitites.MangaItem

interface Repository{

    fun getAnimePage(): LiveData<PagedList<AnimeItem>>

    fun getMangaPage(): LiveData<PagedList<MangaItem>>

    val responseCallback: ResponseCallback
}
