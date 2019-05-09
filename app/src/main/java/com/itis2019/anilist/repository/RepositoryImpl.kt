package com.itis2019.anilist.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.itis2019.anilist.api.JikanApiService
import com.itis2019.anilist.db.AnimeDao
import com.itis2019.anilist.entitites.AnimeItem
import com.itis2019.anilist.utils.EXTRA_PAGE_SIZE

class RepositoryImpl(
    apiService: JikanApiService,
    private val animeDao: AnimeDao
) : Repository {

    private val animeBoundaryCallback = AnimeBoundaryCallback(apiService, animeDao)

    override val responseCallback: ResponseCallback
        get() = animeBoundaryCallback

    override fun getAnimePage(): LiveData<PagedList<AnimeItem>> =
        LivePagedListBuilder(animeDao.getAll(), EXTRA_PAGE_SIZE)
            .setBoundaryCallback(animeBoundaryCallback)
            .build()
}
