package com.itis2019.anilist.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.itis2019.anilist.api.JikanApiService
import com.itis2019.anilist.db.AnimeDao
import com.itis2019.anilist.db.MangaDao
import com.itis2019.anilist.entitites.AnimeItem
import com.itis2019.anilist.entitites.MangaItem
import com.itis2019.anilist.utils.EXTRA_PAGE_SIZE

class RepositoryImpl(
    apiService: JikanApiService,
    private val animeDao: AnimeDao,
    private val mangaDao: MangaDao
) : Repository {

    private val animeBoundaryCallback = AnimeBoundaryCallback(apiService, animeDao)
    private val mangaBoundaryCallback = MangaBoundaryCallback(apiService, mangaDao)

    override val responseCallback: ResponseCallback
        get() = animeBoundaryCallback

    override fun getAnimePage(): LiveData<PagedList<AnimeItem>> =
        LivePagedListBuilder(animeDao.getAll(), EXTRA_PAGE_SIZE)
            .setBoundaryCallback(animeBoundaryCallback)
            .build()

    override fun getMangaPage(): LiveData<PagedList<MangaItem>> =
        LivePagedListBuilder(mangaDao.getAll(), EXTRA_PAGE_SIZE).setBoundaryCallback(mangaBoundaryCallback)
            .build()

}
