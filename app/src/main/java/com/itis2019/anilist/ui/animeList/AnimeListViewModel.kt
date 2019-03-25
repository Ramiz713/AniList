package com.itis2019.anilist.ui.animeList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import android.widget.ImageView
import com.itis2019.anilist.entitites.AnimeItem
import com.itis2019.anilist.entitites.Response
import com.itis2019.anilist.repository.Repository
import com.itis2019.anilist.utils.SingleLiveEvent
import io.reactivex.rxkotlin.subscribeBy

class AnimeListViewModel(private val repository: Repository) : ViewModel() {

    private val loadingLiveData = MutableLiveData<Boolean>()
    private var pagedListAnime = MutableLiveData<Response<PagedList<AnimeItem>>>()

    fun isLoading(): LiveData<Boolean> = loadingLiveData

    val navigateToAnimeDetails = SingleLiveEvent<Pair<AnimeItem, ImageView>>()

    fun movieClicked(pair: Pair<AnimeItem, ImageView>) = navigateToAnimeDetails.setValue(pair)

    @Suppress("CheckResult")
    @MainThread
    fun onLoadNextPage(): LiveData<Response<PagedList<AnimeItem>>> {
        repository.getAnimePage()
            .doOnSubscribe { loadingLiveData.setValue(true) }
            .doAfterNext { loadingLiveData.setValue(false) }
            .subscribeBy(onNext = {
                val pagedListAnimeImm = pagedListAnime
                pagedListAnimeImm.value = Response.success(it)
                pagedListAnime = pagedListAnimeImm

            }, onError = {
                val pagedListAnimeImm = pagedListAnime
                pagedListAnime.value = Response.error(it)
                pagedListAnime = pagedListAnimeImm
            })
        return pagedListAnime
    }
}
