package com.itis2019.anilist.ui.animeList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import android.widget.ImageView
import com.itis2019.anilist.entitites.AnimeItem
import com.itis2019.anilist.repository.Repository
import com.itis2019.anilist.utils.SingleLiveEvent

class AnimeListViewModel(private val repository: Repository) : ViewModel() {

    private val pagedListAnime: LiveData<PagedList<AnimeItem>> = repository.getAnimePage()

    fun isLoading(): LiveData<Boolean> = repository.responseCallback.isLoading
    fun isError(): LiveData<Throwable> = repository.responseCallback.isError

    val navigateToAnimeDetails = SingleLiveEvent<Pair<AnimeItem, ImageView>>()

    fun movieClicked(pair: Pair<AnimeItem, ImageView>) {
        navigateToAnimeDetails.value = pair
    }

    @Suppress("CheckResult")
    @MainThread
    fun onLoadNextPage(): LiveData<PagedList<AnimeItem>> = pagedListAnime
}
