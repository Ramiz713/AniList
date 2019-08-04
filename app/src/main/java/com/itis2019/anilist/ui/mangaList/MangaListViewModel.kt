package com.itis2019.anilist.ui.mangaList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import android.widget.ImageView
import com.itis2019.anilist.entitites.MangaItem
import com.itis2019.anilist.repository.Repository
import com.itis2019.anilist.utils.SingleLiveEvent

class MangaListViewModel(private val repository: Repository) : ViewModel() {

    private val pagedListManga: LiveData<PagedList<MangaItem>> = repository.getMangaPage()

    fun isLoading(): LiveData<Boolean> = repository.responseCallback.isLoading
    fun isError(): LiveData<Throwable> = repository.responseCallback.isError

    val navigateToAnimeDetails = SingleLiveEvent<Pair<MangaItem, ImageView>>()

    fun movieClicked(pair: Pair<MangaItem, ImageView>) {
        navigateToAnimeDetails.value = pair
    }

    @Suppress("CheckResult")
    @MainThread
    fun onLoadNextPage(): LiveData<PagedList<MangaItem>> = pagedListManga
}
