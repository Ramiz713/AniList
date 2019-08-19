package com.itis2019.anilist.ui.mangaList

import android.widget.ImageView
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.itis2019.anilist.entitites.manga.MangaItem
import com.itis2019.anilist.repository.Repository
import com.itis2019.anilist.utils.SingleLiveEvent

class MangaListViewModel(private val repository: Repository) : ViewModel() {

    private val pagedListManga: LiveData<PagedList<MangaItem>> = repository.getMangaLivePagedList()

    fun isLoading(): LiveData<Boolean> = repository.mangaResponseCallback.isLoading
    fun isError(): LiveData<Throwable> = repository.mangaResponseCallback.isError

    val navigateToAnimeDetails = SingleLiveEvent<Pair<MangaItem, ImageView>>()

    fun movieClicked(pair: Pair<MangaItem, ImageView>) {
        navigateToAnimeDetails.value = pair
    }

    @Suppress("CheckResult")
    @MainThread
    fun onLoadNextPage(): LiveData<PagedList<MangaItem>> = pagedListManga
}
