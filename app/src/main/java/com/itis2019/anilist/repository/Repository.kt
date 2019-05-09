package com.itis2019.anilist.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.itis2019.anilist.entitites.AnimeItem

interface Repository{
    fun getAnimePage(): LiveData<PagedList<AnimeItem>>
    val responseCallback: ResponseCallback
}
