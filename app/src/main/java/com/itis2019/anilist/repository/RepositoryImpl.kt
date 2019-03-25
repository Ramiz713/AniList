package com.itis2019.anilist.repository

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.itis2019.anilist.api.JikanApiService
import com.itis2019.anilist.db.AnimeDao
import com.itis2019.anilist.entitites.AnimeItem
import com.itis2019.anilist.utils.EXTRA_PAGE_SIZE
import io.reactivex.Observable

class RepositoryImpl(
    private val apiService: JikanApiService,
    private val animeDao: AnimeDao
) : Repository {
    override fun getAnimePage(): Observable<PagedList<AnimeItem>> =
        RxPagedListBuilder(animeDao.getAll(), EXTRA_PAGE_SIZE)
            .setBoundaryCallback(AnimeBoundaryCallback(apiService, animeDao))
            .buildObservable()
}
