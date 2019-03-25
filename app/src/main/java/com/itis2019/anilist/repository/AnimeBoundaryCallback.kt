package com.itis2019.anilist.repository

import android.arch.paging.PagedList
import com.itis2019.anilist.api.JikanApiService
import com.itis2019.anilist.db.AnimeDao
import com.itis2019.anilist.entitites.AnimeItem
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AnimeBoundaryCallback(private val apiService: JikanApiService, private val animeDao: AnimeDao) :
    PagedList.BoundaryCallback<AnimeItem>() {

    private var page = 1

    @Suppress("CheckResult")
    override fun onZeroItemsLoaded() {
        getTopAnimeList()
    }

    override fun onItemAtEndLoaded(itemAtEnd: AnimeItem) {
        getTopAnimeList()
    }

    private fun getTopAnimeList() = apiService
        .getTopAnimeList(page)
        .map { it.top }
        .doOnSuccess { animeDao.insert(it) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribeBy { page++ }
}
