package com.itis2019.anilist.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.itis2019.anilist.api.JikanApiService
import com.itis2019.anilist.db.AnimeDao
import com.itis2019.anilist.db.MangaDao
import com.itis2019.anilist.entitites.anime.AnimeItem
import com.itis2019.anilist.entitites.manga.MangaItem
import com.itis2019.anilist.utils.EXTRA_PAGE_SIZE

class RepositoryImpl(
    private val apiService: JikanApiService,
    private val animeDao: AnimeDao,
    private val mangaDao: MangaDao
) : Repository {

    private val animeBoundaryCallback = AnimeBoundaryCallback(apiService, animeDao)
    private val mangaBoundaryCallback = MangaBoundaryCallback(apiService, mangaDao)

    private val animeLivePagedList =
        LivePagedListBuilder(animeDao.getAll(), EXTRA_PAGE_SIZE)
            .setBoundaryCallback(animeBoundaryCallback)
            .build()

    private val mangaLivePagedList =
        LivePagedListBuilder(mangaDao.getAll(), EXTRA_PAGE_SIZE)
            .setBoundaryCallback(mangaBoundaryCallback)
            .build()

    override val animeResponseCallback: ResponseCallback
        get() = animeBoundaryCallback

    override val mangaResponseCallback: ResponseCallback
        get() = mangaBoundaryCallback

    override fun getAnimeLivePagedList(): LiveData<PagedList<AnimeItem>> = animeLivePagedList

    override fun getMangaLivePagedList(): LiveData<PagedList<MangaItem>> = mangaLivePagedList

    override suspend fun getAnimeItem(id: Int): AnimeItem {
        val animeItem = apiService.getAnimeItemAsync(id)
        animeDao.update(animeItem)
        return animeItem
    }

}
