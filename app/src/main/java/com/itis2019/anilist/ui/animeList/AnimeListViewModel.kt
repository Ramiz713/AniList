package com.itis2019.anilist.ui.animeList

import android.widget.ImageView
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.itis2019.anilist.entitites.anime.AnimeItem
import com.itis2019.anilist.repository.Repository
import com.itis2019.anilist.utils.SingleLiveEvent

class AnimeListViewModel(private val repository: Repository) : ViewModel() {

    private val pagedListAnime: LiveData<PagedList<AnimeItem>> = repository.getAnimeLivePagedList()

    fun isLoading(): LiveData<Boolean> = repository.animeResponseCallback.isLoading
    fun isError(): LiveData<Throwable> = repository.animeResponseCallback.isError

    val navigateToAnimeDetails = SingleLiveEvent<Pair<AnimeItem, ImageView>>()

    fun movieClicked(pair: Pair<AnimeItem, ImageView>) {
        navigateToAnimeDetails.value = pair
    }

    @Suppress("CheckResult")
    @MainThread
    fun onLoadNextPage(): LiveData<PagedList<AnimeItem>> = pagedListAnime
}
